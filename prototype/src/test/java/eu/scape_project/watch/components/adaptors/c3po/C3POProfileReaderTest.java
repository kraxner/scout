package eu.scape_project.watch.components.adaptors.c3po;

import java.io.File;
import java.io.FileNotFoundException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests the profile parser.
 * 
 * @author Petar Petrov <me@petarpetrov.org>
 * 
 */
public class C3POProfileReaderTest {

  /**
   * The object under test.
   */
  private C3POProfileReader reader;

  @Before
  public void setup() {
    try {
      this.reader = new C3POProfileReader(new File("src/test/resources/profiles/dummy_profile.xml"));
    } catch (final FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @After
  public void tearDown() {
    this.reader = null;
  }

  @Test
  public void shouldObtainCollectionName() throws Exception {
    final String name = this.reader.getCollectionName();
    Assert.assertEquals("coll-0-test", name);
  }

  @Test
  public void shouldObtainObjectsCount() throws Exception {
    final String count = this.reader.getObjectsCount();
    Assert.assertEquals("502", count);
  }

  @Ignore("Overall size feature not yet implemented") @Test
  public void shouldObtainCollectionSize() throws Exception {
    final String size = this.reader.getCollectionSize();
    Assert.assertEquals("fail", size);
  }

  @Test
  public void shouldObtainObjectsMinSize() throws Exception {
    final String size = this.reader.getObjectsMinSize();
    Assert.assertEquals("42", size);
  }

  @Test
  public void shouldObtainObjectsMaxSize() throws Exception {
    final String size = this.reader.getObjectsMaxSize();
    Assert.assertEquals("10000000", size);
  }

  @Test
  public void shouldObtainObjectsAvgSize() throws Exception {
    final String size = this.reader.getObjectsAvgSize();
    Assert.assertEquals("35000.42", size);
  }
}