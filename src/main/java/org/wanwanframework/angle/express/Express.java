package org.wanwanframework.angle.express;

import java.io.File;
import java.util.Arrays;

public class Express {

	public String content;

	public File[] dir;

	@Override
	public String toString() {
		return "Express [content=" + content + ", dir=" + Arrays.toString(dir) + "]";
	}

}
