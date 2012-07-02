package eu.scape_project.watch.adaptor.c3po;

import static eu.scape_project.watch.adaptor.c3po.common.C3POConstants.CP_COLLECTION_SIZE;
import static eu.scape_project.watch.adaptor.c3po.common.C3POConstants.CP_DESCRIPTION;
import static eu.scape_project.watch.adaptor.c3po.common.C3POConstants.CP_DISTRIBUTION;
import static eu.scape_project.watch.adaptor.c3po.common.C3POConstants.CP_NAME;
import static eu.scape_project.watch.adaptor.c3po.common.C3POConstants.CP_OBJECTS_AVG_SIZE;
import static eu.scape_project.watch.adaptor.c3po.common.C3POConstants.CP_OBJECTS_COUNT;
import static eu.scape_project.watch.adaptor.c3po.common.C3POConstants.CP_OBJECTS_MAX_SIZE;
import static eu.scape_project.watch.adaptor.c3po.common.C3POConstants.CP_OBJECTS_MIN_SIZE;
import static eu.scape_project.watch.adaptor.c3po.common.C3POConstants.ENDPOINT_CNF;
import static eu.scape_project.watch.adaptor.c3po.common.C3POConstants.ENDPOINT_DEFAULT;
import static eu.scape_project.watch.adaptor.c3po.common.C3POConstants.ENDPOINT_DESC;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.scape_project.watch.adaptor.c3po.client.C3POClient;
import eu.scape_project.watch.adaptor.c3po.client.C3POClientInterface;
import eu.scape_project.watch.adaptor.c3po.client.C3PODummyClient;
import eu.scape_project.watch.adaptor.c3po.command.CollectionSizeCommand;
import eu.scape_project.watch.adaptor.c3po.command.Command;
import eu.scape_project.watch.adaptor.c3po.command.DistributionCommand;
import eu.scape_project.watch.adaptor.c3po.command.ObjectsAvgSizeCommand;
import eu.scape_project.watch.adaptor.c3po.command.ObjectsCountCommand;
import eu.scape_project.watch.adaptor.c3po.command.ObjectsMaxSizeCommand;
import eu.scape_project.watch.adaptor.c3po.command.ObjectsMinSizeCommand;
import eu.scape_project.watch.adaptor.c3po.common.C3POProfileReader;
import eu.scape_project.watch.adaptor.c3po.common.ProfileResult;
import eu.scape_project.watch.domain.Entity;
import eu.scape_project.watch.domain.EntityType;
import eu.scape_project.watch.domain.Property;
import eu.scape_project.watch.domain.PropertyValue;
import eu.scape_project.watch.interfaces.AdaptorPluginInterface;
import eu.scape_project.watch.interfaces.PluginType;
import eu.scape_project.watch.interfaces.ResultInterface;
import eu.scape_project.watch.utils.ConfigParameter;
import eu.scape_project.watch.utils.exceptions.InvalidParameterException;
import eu.scape_project.watch.utils.exceptions.PluginException;

/**
 * A watch conforming adaptor for a collection profile source called c3po.
 * 
 * @author Petar Petrov <me@petarpetrov.org>
 * @version 0.0.2
 */
public class C3POAdaptor implements AdaptorPluginInterface {

  /**
   * Default logger for this adaptor.
   */
  private static final Logger LOG = LoggerFactory.getLogger(C3POAdaptor.class);

  /**
   * The current version of the adaptor.
   */
  private static final String VERSION = "0.0.2";

  /**
   * The current config of c3po.
   */
  private Map<String, String> config;

  /**
   * The commands for property extraction.
   */
  private Map<String, Command> commands;

  /**
   * The default configs.
   */
  private List<ConfigParameter> defaultConfig;

  /**
   * The source client.
   */
  private C3POClientInterface source;

  /**
   * Initializes this plugin.
   * 
   */
  @Override
  public void init() {
    this.initConfigs();

    final String formatDistr = "format";

    this.commands = new HashMap<String, Command>();
    this.commands.put(CP_COLLECTION_SIZE, new CollectionSizeCommand());
    this.commands.put(CP_OBJECTS_COUNT, new ObjectsCountCommand());
    this.commands.put(CP_OBJECTS_MAX_SIZE, new ObjectsMaxSizeCommand());
    this.commands.put(CP_OBJECTS_MIN_SIZE, new ObjectsMinSizeCommand());
    this.commands.put(CP_OBJECTS_AVG_SIZE, new ObjectsAvgSizeCommand());
    this.commands.put(String.format(CP_DISTRIBUTION, formatDistr), new DistributionCommand(formatDistr));
  }

  /**
   * Shuts down the plugin and clears all resources. Warning, reusing the plugin
   * after a shutdown call (without calling {@link C3POAdaptor#init()} again)
   * may lead to unexpected behaviour.
   */
  @Override
  public void shutdown() {
    this.defaultConfig.clear();
    this.config.clear();
    this.commands.clear();

    // close connections, cancel jobs, etc.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return "c3po";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getVersion() {
    return VERSION;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription() {
    return "A scout adaptor for the c3po content profiler source";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PluginType getPluginType() {
    return PluginType.ADAPTOR;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ConfigParameter> getParameters() {
    return this.defaultConfig;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<String, String> getParameterValues() {
    return this.config;
  }

  /**
   * {@inheritDoc}
   * 
   * @throws InvalidParameterException
   *           if some of the required values is not provided or has a null
   *           value.
   */
  @Override
  public void setParameterValues(final Map<String, String> values) throws InvalidParameterException {
    final Set<String> keys = values.keySet();

    for (ConfigParameter cp : this.defaultConfig) {
      final String key = cp.getKey();
      if (cp.isRequired() && (!keys.contains(key) || values.get(key) == null)) {
        throw new InvalidParameterException("No value set for the required config parameter: " + key);
      }
    }

    this.config = values;

  }

  /**
   * Fetches all values of all collections in the pre-configured c3po instance.
   * This method should be used with caution as it might lead to a huge network
   * overhead, if the c3po instance has a great deal of collections.
   * 
   * @return a {@link ProfileResult} result.
   * 
   * @throws PluginException
   *           if the c3po client cannot be instantiated or another error (e.g.
   *           network problems) occur.
   */
  @Override
  public ResultInterface execute() throws PluginException {
    LOG.info("Executing c3po adaptor, fetching all properties for all known collections.");
    this.createSource();

    final List<String> identifiers = this.source.getCollectionIdentifiers();

    LOG.debug("c3po adaptor has found {} collections, ... fetching", identifiers.size());

    final ProfileResult result = new ProfileResult();
    final EntityType et = new EntityType(CP_NAME, CP_DESCRIPTION);

    for (String id : identifiers) {
      final Entity e = new Entity(et, id);
      final List<PropertyValue> values = this.getPropertyValues(id, null);

      for (PropertyValue v : values) {
        v.setEntity(e);
      }

      result.add(values);
    }

    return result;
  }

  /**
   * Not yet implemented.
   * 
   * @param context
   *          a map containing the entities and the properties to fetch.
   * @return nothing.
   * 
   * @throws PluginException
   *           always.
   */
  @Override
  public ResultInterface execute(final Map<Entity, List<Property>> context) throws PluginException {
    throw new PluginException("This method is not yet implemented!");
  }

  // ### private methods.

  /**
   * Initializes the default configuration of the adaptor. Currently it starts a
   * dummy adaptor.
   */
  private void initConfigs() {
    this.config = new HashMap<String, String>();

    this.defaultConfig = new ArrayList<ConfigParameter>();
    this.defaultConfig.add(new ConfigParameter(ENDPOINT_CNF, ENDPOINT_DEFAULT, ENDPOINT_DESC, true));

    for (final ConfigParameter cp : this.defaultConfig) {
      this.config.put(cp.getKey(), cp.getValue());
    }
  }

  /**
   * Queries the c3po source for a content profile of the collection with the
   * given id.
   * 
   * @param id
   *          the id of the collection.
   * @return an {@link InputStream} containing the file.
   */
  private InputStream getCollectionProfile(final String id) {
    // final List<String> expanded = this.generatePropertyExpansionList(null);
    final InputStream is = this.source.getCollectionProfile(id, null);
    return is;
  }

  /**
   * Creates the source client based on the current configuration.
   * 
   * @throws PluginException
   *           if the current configuration is not correct or does not provide
   *           correct values.
   */
  private void createSource() throws PluginException {
    final String endpoint = this.getParameterValues().get(ENDPOINT_CNF);
    if (endpoint == null || endpoint.equals("")) {
      throw new PluginException("The endpoint value is invalued: " + endpoint);
    }

    if (endpoint.equals(ENDPOINT_DEFAULT)) {
      this.source = new C3PODummyClient();
    } else {
      this.source = new C3POClient(endpoint);
    }

    if (this.source == null) {
      throw new PluginException("An error occurred, could not initialize a c3po client");
    }
  }

  /**
   * Gets the values of the given properties for a collection with the given id.
   * If the list is null or empty, then all known properties will be fetched. If
   * there is no collection with the given id with the source the method returns
   * an empty list.
   * 
   * @param id
   *          the id of the collection in the source.
   * @param props
   *          the properties to fetch from the collection.
   * @return a list of {@link PropertyValue} objects containing the results.
   */
  private List<PropertyValue> getPropertyValues(final String id, final List<Property> props) {
    final InputStream stream = this.getCollectionProfile(id);
    final List<PropertyValue> values = new ArrayList<PropertyValue>();

    if (stream != null) {
      final C3POProfileReader reader = new C3POProfileReader(this.source.getReader(), stream);
      this.setupCommands(reader);
      if (props == null || props.size() == 0) {

        for (String c : this.commands.keySet()) {
          final Command cmd = this.commands.get(c);
          final PropertyValue pv = cmd.execute();
          values.add(pv);
        }
      } else {
        // TODO start only those matching the property names.
        throw new RuntimeException("Not yet implemented");
      }
    }

    LOG.info("Returning property values...");
    return values;
  }

  /**
   * Initializes the commands that will be used.
   * 
   * @param reader
   *          the profile reader.
   */
  private void setupCommands(final C3POProfileReader reader) {
    for (Command cmd : this.commands.values()) {
      cmd.setReader(reader);
    }
  }

  /**
   * A convenience method that generates a list with the property name.
   * 
   * @param property
   *          the property.
   * @return the list with the name of the property.
   */
  private List<String> generatePropertyExpansionList(final Property property) {
    List<String> props = new ArrayList<String>();
    if (property != null) {
      LOG.debug("generating parameter for property {}", property.getName());
      props = Arrays.asList(property.getName());
    }
    return props;
  }

}
