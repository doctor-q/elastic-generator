package cc.doctor.generator;

import cn.org.rapid_framework.generator.GeneratorFacade;

public class GeneratorMain {

	public static void generate() throws Exception {
		GeneratorFacade g = new GeneratorFacade();
		g.deleteOutRootDir();

		g.getGenerator().setTemplateRootDir("classpath:template");
		g.generateByAllTable();
	}

	public static void main(String[] args) throws Exception {
		generate();
	}
}
