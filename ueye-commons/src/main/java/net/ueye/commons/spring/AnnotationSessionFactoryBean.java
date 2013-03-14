package net.ueye.commons.spring;

import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-20
 */
public class AnnotationSessionFactoryBean extends LocalSessionFactoryBean {

	private AnnotationScan annotationScan;

	public void setAnnotationScan(AnnotationScan annotationScan) {
		this.annotationScan = annotationScan;
		this.setPackagesToScan(annotationScan.getAnnotationPackages());
		this.setAnnotatedClasses(annotationScan.getAnnotationClasses());
	}

	public AnnotationScan getAnnotationScan() {
		return annotationScan;
	}

}
