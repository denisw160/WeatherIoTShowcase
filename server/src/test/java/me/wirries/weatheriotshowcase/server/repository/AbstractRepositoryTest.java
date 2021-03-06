package me.wirries.weatheriotshowcase.server.repository;

import me.wirries.weatheriotshowcase.server.AbstractApplicationTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This is an abstract test case for all repository tests.
 */
public abstract class AbstractRepositoryTest extends AbstractApplicationTest {

    protected abstract JpaRepository getRepository();

    @Before
    public final void setUp() throws Exception {
        initData();
    }

    protected void initData() {
        // default no data is created
    }

    @After
    public final void tearDown() throws Exception {
        clearData();
    }

    protected void clearData() {
        // default no data is deleted
    }

    @Test
    public void testFindAll() throws Exception {
        final List list = getRepository().findAll();
        Assert.assertNotNull(list);
    }

}
