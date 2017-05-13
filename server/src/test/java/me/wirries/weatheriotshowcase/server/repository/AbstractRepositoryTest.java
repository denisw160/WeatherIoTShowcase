package me.wirries.weatheriotshowcase.server.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * This is an abstract test case for all repository tests.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class AbstractRepositoryTest {

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
