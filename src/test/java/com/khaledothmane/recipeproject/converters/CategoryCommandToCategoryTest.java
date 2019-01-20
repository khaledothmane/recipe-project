package com.khaledothmane.recipeproject.converters;

import com.khaledothmane.recipeproject.commands.CategoryCommand;
import com.khaledothmane.recipeproject.model.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    final Long CATEGORY_ID = 1L;
    final String CATEGORY_NAME = "CAT_1";
    CategoryCommandToCategory bean;

    @Before
    public void setUp() throws Exception {
        bean = new CategoryCommandToCategory();
    }

    @After
    public void tearDown() throws Exception {
        bean = null;
    }

    @Test
    public void testNullBean() throws Exception {
        assertNull(bean.convert(null));
    }

    @Test
    public void convert() {
        CategoryCommand in = new CategoryCommand();
        in.setId(CATEGORY_ID);
        in.setName(CATEGORY_NAME);

        Category out = bean.convert(in);

        assertEquals(in.getId(), out.getId());
        assertEquals(in.getName(), out.getName());
    }
}