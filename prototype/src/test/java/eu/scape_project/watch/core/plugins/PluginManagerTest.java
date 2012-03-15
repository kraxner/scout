package eu.scape_project.watch.core.plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import junit.framework.Assert;
import eu.scape_project.watch.core.plugin.Plugin;
import eu.scape_project.watch.core.plugin.PluginException;
import eu.scape_project.watch.core.plugin.PluginInfo;
import eu.scape_project.watch.core.plugin.PluginManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests the plugin manager.
 * 
 * @author Petar Petrov <me@petarpetrov.org>
 * 
 */
public class PluginManagerTest {

  /**
   * A default logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(PluginManagerTest.class);

  /**
   * A default buffer size.
   */
  private static final int BUFFER_SIZE = 1024;

  /**
   * 1 second in ms.
   */
  private static final int SECOND = 1000;

  /**
   * The object under test.
   */
  private PluginManager manager;

  /**
   * Gets the plugin manager.
   */
  @Before
  public void setUp() {
    this.manager = PluginManager.getDefaultPluginManager();
  }

  /**
   * Destroys the plugin manager and removes the dummy adaptors.
   */
  @After
  public void tearDown() {
    this.manager.shutdown();

    final File path = new File("src/test/resources/plugins");
    this.deleteFolder(path);
  }

  /**
   * Tests the creation of the manager.
   * 
   */
  @Test
  public void shouldObtainPluginManager() {
    this.manager = PluginManager.getDefaultPluginManager();
    Assert.assertNotNull(this.manager);

  }

  /**
   * Tests the loading of a plugin.
   * 
   * @throws Exception
   *           if something goes wrong.
   */
  @Test
  public void shouldLoadPlugin() throws Exception {
    List<PluginInfo> info = this.manager.getPluginInfo();
    Assert.assertEquals(0, info.size());
    copyTestJar("testadaptor.jar");

    this.manager.reScan();
    this.sleep();

    info = this.manager.getPluginInfo();
    Assert.assertEquals(1, info.size());

    final PluginInfo i1 = info.get(0);
    final Plugin plugin = this.manager.getPlugin(i1.getClassName(), i1.getVersion());
    plugin.execute();
    Assert.assertNotNull(plugin);
    Assert.assertEquals("0.1", plugin.getVersion());
  }

  /**
   * Tests that the init method shall not be called twice.
   * 
   * @throws Exception
   *           when the init method gets called for the second time.
   */
  @Test(expected = PluginException.class)
  public void shouldNotCallInitTwoTimes() throws Exception {
    copyTestJar("testadaptor.jar");

    this.manager.reScan();
    this.sleep();

    final List<PluginInfo> info = this.manager.getPluginInfo();
    final PluginInfo i1 = info.get(0);
    final Plugin plugin = this.manager.getPlugin(i1.getClassName(), i1.getVersion());
    plugin.init();

    Assert.fail("This code should not have been executed!");
  }

  /**
   * Tests the loading of two versions of the same class.
   * 
   * @throws Exception
   *           if something goes wrong.
   */
  @Test
  public void shouldLoadTwoVersionsOfAdaptor() throws Exception {
    copyTestJar("testadaptor.jar");
    copyTestJar("testadaptor-0.2.jar");
    this.manager.reScan();
    this.sleep();

    final List<PluginInfo> info = this.manager.getPluginInfo();
    Assert.assertEquals(2, info.size());
    final PluginInfo i1 = info.get(0);
    final PluginInfo i2 = info.get(1);
    final Plugin p1 = this.manager.getPlugin(i1.getClassName(), i1.getVersion());
    final Plugin p2 = this.manager.getPlugin(i2.getClassName(), i2.getVersion());
    Assert.assertNotSame(i1.getVersion(), i2.getVersion());

    LOG.debug("Executing plugin 1: {}-{}", p1.getName(), p1.getVersion());
    p1.execute();
    LOG.debug("Executing plugin 2: {}-{}", p2.getName(), p2.getVersion());
    p2.execute();
  }

  /**
   * Copies the passed jar to the test plugin directory.
   * 
   * @param s
   *          the name of the jar.
   */
  private void copyTestJar(final String s) {
    InputStream inStream = null;
    OutputStream outStream = null;

    try {

      final File source = new File("src/test/resources/" + s);
      final File target = new File("src/test/resources/plugins/" + s);

      inStream = new FileInputStream(source);
      outStream = new FileOutputStream(target);

      final byte[] buffer = new byte[BUFFER_SIZE];

      int length;
      // copy the file content in bytes
      while ((length = inStream.read(buffer)) > 0) {
        outStream.write(buffer, 0, length);
      }
      inStream.close();
      outStream.close();
      LOG.debug("Plugin is copied successful!");
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Deletes all files and folders under the given path.
   * 
   * @param path
   *          the path to delete.
   */
  private void deleteFolder(final File path) {
    if (path.exists()) {
      final File[] files = path.listFiles(new FilenameFilter() {

        @Override
        public boolean accept(final File file, final String name) {
          return name.endsWith(".jar");
        }
      });
      for (final File f : files) {
        if (f.isDirectory()) {
          this.deleteFolder(f);
        } else {
          f.delete();
        }
      }
    }

  }

  /**
   * Sleeps for 1 second, giving the plugin manager chance to load the files.
   */
  private void sleep() {
    try {
      Thread.sleep(SECOND);
    } catch (final InterruptedException e) {
      // swallow
    }
  }

}
