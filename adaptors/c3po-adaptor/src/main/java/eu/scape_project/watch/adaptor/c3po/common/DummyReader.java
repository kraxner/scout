package eu.scape_project.watch.adaptor.c3po.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A content profile parser. Currently it parses the preliminary content profile
 * aggregation and gets only a few values out of it.
 * 
 * @author Petar Petrov <me@petarpetrov.org>
 * 
 */
public class DummyReader implements ProfileVersionReader {

  /**
   * A default logger for this class.
   */
  private static final Logger LOG = LoggerFactory.getLogger(DummyReader.class);

  /**
   * The xml document representation of the profile.
   */
  private Document doc;

  /**
   * Initializes the xml document from the passed stream.
   * 
   * @param is
   *          the stream to read the document of.
   */
  public void setStream(final InputStream is) {
    this.getDocument(is);
  }

  /**
   * Retrieves the collection name or identifier of the content profile.
   * 
   * @return the identifying name.
   */
  public String getCollectionName() {
    final Element collection = this.getCollectionElement();
    return this.getAttributeValue(collection, "name");
  }

  /**
   * Retrieves the objects count in the collection.
   * 
   * @return a string representation of the count of objects.
   */
  public String getObjectsCount() {
    final Element collection = this.getCollectionElement();
    return this.getAttributeValue(collection, "elements");
  }

  /**
   * Retrieves the size of the whole collection.
   * 
   * @return a string representation of the size.
   */
  public String getCollectionSize() {
    final Element size = this.getSizePropertyElement();
    return this.getAttributeValue(size, "sum");
  }

  /**
   * Retrieves the size of the largest object in the collection.
   * 
   * @return a string representation of the max size.
   */
  public String getObjectsMaxSize() {
    final Element size = this.getSizePropertyElement();
    return this.getAttributeValue(size, "max");
  }

  /**
   * Retrieves the size of the smallest object in the collection.
   * 
   * @return a string representation of the min size.
   */
  public String getObjectsMinSize() {
    final Element size = this.getSizePropertyElement();
    return this.getAttributeValue(size, "min");
  }

  /**
   * Retrieves the average size of all objects in the collection.
   * 
   * @return the average size.
   */
  public String getObjectsAvgSize() {
    final Element size = this.getSizePropertyElement();
    return this.getAttributeValue(size, "avg");
  }

  /**
   * Parses the distribution of the passed property if it is provided in the xml
   * document. The method returns the distribution in a map or null if the
   * profile didn't contain this property or if no distribution was provided.
   * 
   * @param name
   *          the name of the property.
   * @return the distribution map.
   */
  public Map<String, String> getDistribution(final String name) {
    final Element property = this.getPropertyElement(name);
    final boolean exists = Boolean.valueOf(this.getAttributeValue(property, "expanded"));

    if (exists) {
      final Map<String, String> distribution = new HashMap<String, String>();
      final List<?> items = property.selectNodes("//properties/property[@id='" + name + "']/*");

      for (Object o : items) {
        final Element e = (Element) o;
        final String key = this.getAttributeValue(e, "value");
        final String value = this.getAttributeValue(e, "count");
        distribution.put(key, value);
      }

      return distribution;

    }

    return null;
  }

  /**
   * Returns the collection element tag.
   * 
   * @return the collection xml element.
   */
  private Element getCollectionElement() {
    if (this.doc == null) {
      return null;
    }

    final Element root = doc.getRootElement();
    final Element collection = (Element) root.elementIterator().next();

    return collection;
  }

  /**
   * Obtains the size property element in the xml.
   * 
   * @return the property tag with id = size.
   */
  private Element getSizePropertyElement() {
    return this.getPropertyElement("size");
  }

  /**
   * Retrieves the Property element for the specified property.
   * 
   * @param name
   *          the name of the property.
   * @return the xml Element.
   */
  private Element getPropertyElement(final String name) {
    final Element collection = this.getCollectionElement();
    final List<?> nodes = collection.selectNodes("//properties/property[@id='" + name + "']");

    Element e = null;

    if (nodes.size() == 1) {
      e = (Element) nodes.get(0);
    }

    return e;
  }

  /**
   * Retrieves the attribute value with the givenn ame of the given xml element.
   * If no such value exists, then {@link DummyReader#MISSING_VALUE} is
   * returned.
   * 
   * @param element
   *          the element in which the attribute resides.
   * @param name
   *          the name of the attribute
   * @return the value of the attribute.
   */
  private String getAttributeValue(final Element element, final String name) {
    if (element != null) {
      final Attribute attribute = element.attribute(name);
      return attribute.getValue();
    }

    return MISSING_VALUE;
  }

  /**
   * Retrieves the xml document out of the input stream.
   * 
   * @param is
   *          the input stream to read.
   */
  private void getDocument(final InputStream is) {
    try {
      final SAXReader reader = new SAXReader();
      this.doc = reader.read(is);

    } catch (final DocumentException e) {
      LOG.error("An error occurred while reading the profile: {}", e.getMessage());
      this.doc = null;
    }

    try {
      is.close();
    } catch (final IOException e) {
      LOG.error("An error occurred while closing the input stream: {}", e.getMessage());
    }
  }

}
