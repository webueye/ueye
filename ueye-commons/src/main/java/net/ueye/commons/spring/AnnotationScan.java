package net.ueye.commons.spring;


/**
 * @author rubys@vip.qq.com
 * @since 2012-8-20
 */
public class AnnotationScan {

	private String[] annotationPackages;
	private Class<?>[] annotationClasses;

	public void setAnnotationClasses(Class<?>[] annotationClasses) {
		this.annotationClasses = annotationClasses;
	}

	public Class<?>[] getAnnotationClasses() {
		return annotationClasses;
	}

	public void setAnnotationPackages(String[] annotationPackages) {
		this.annotationPackages = annotationPackages;
	}

	public String[] getAnnotationPackages() {
		return annotationPackages;
	}

}
