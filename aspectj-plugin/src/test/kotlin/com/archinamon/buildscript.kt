package com.archinamon

const val REPOSITORIES = """
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
"""

const val SIMPLE_PLUGIN_IMPLYING = """
    apply plugin: 'com.android.application'
    apply plugin: 'com.archinamon.aspectj'

    android {
        compileSdkVersion 27

        defaultConfig {
            applicationId 'com.example.test'
            minSdkVersion 21
            targetSdkVersion 27
            versionCode 1
            versionName "1.0"
        }
    }

    repositories {
        google()
        jcenter()
        mavenCentral()
    }
"""

const val DEPENDENCIES_WITH_TESTS = """
    dependencies {
        testCompile("junit:junit:4.12")
    }
"""

const val SIMPLE_TEST_PROVIDER_BODY_JAVA = """
    package com.example.test;

    public class DataProvider {

        public String getInfo() {
            return "immutable";
        }
    }
"""

const val SIMPLE_TEST_BODY_JAVA = """
    package com.example.test;

    import org.junit.Test;
    import static junit.framework.Assert.assertEquals;

    public class SimpleTest {

        @Test
        public void testAspectJ() {
            final String mutated = getImmutable();
            assertEquals("mutable", mutated);
        }

        private String getImmutable() {
            return new DataProvider().getInfo();
        }
    }
"""

const val SIMPLE_ASPECT_FOR_TEST_AUGMENTING = """
    package com.example.xpoint;

    privileged aspect TestMutator {

        pointcut mutate():
            within(com.example.test.DataProvider) &&
            execution(String getInfo(..));

        String around(): mutate() {
            return "mutable";
        }
    }
"""