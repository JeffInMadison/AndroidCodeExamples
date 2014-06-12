package com.jeffinmadison.simpleactivity;

import junit.framework.TestCase;

public class FooTest extends TestCase {

    public void testBar() throws Exception {
        assertEquals("12", Foo.bar(1,2));
    }
}