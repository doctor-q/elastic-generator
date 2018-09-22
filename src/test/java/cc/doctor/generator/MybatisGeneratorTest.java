package cc.doctor.generator;

import cn.org.rapid_framework.generator.GeneratorProperties;
import org.junit.After;

import java.io.IOException;

public class MybatisGeneratorTest {
    @After
    public void openDir() throws IOException {
        String dir = GeneratorProperties.getProperty("outRoot");
        String basepackage = GeneratorProperties.getProperty("basepackage").replace(".", "\\");
        Runtime.getRuntime().exec("cmd.exe /c start " + dir + "\\" + basepackage);
    }

}
