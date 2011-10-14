package org.eclipse.emf.ecore.xcore.scoping;

import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;

import com.google.inject.Inject;

import static com.google.common.collect.Maps.*;

public class LazyCreationProxyUriConverter {
	
	@Inject
	private IQualifiedNameConverter nameConverter;

	private Map<String, EClass> types = newHashMap();
	{
		EClass eclass = EcorePackage.Literals.ECLASS;
		EClass genClass = GenModelPackage.Literals.GEN_CLASS;
		EClass genDatatype = GenModelPackage.Literals.GEN_DATA_TYPE;
		EClass genEnum = GenModelPackage.Literals.GEN_ENUM;
		EClass jvmGenericType = TypesPackage.Literals.JVM_GENERIC_TYPE;
		EClass jvmEnumerationType = TypesPackage.Literals.JVM_ENUMERATION_TYPE;
		types.put(eclass.getName(), eclass);
		types.put(genClass.getName(), genClass);
		types.put(genDatatype.getName(), genDatatype);
		types.put(genEnum.getName(), genEnum);
		types.put(jvmGenericType.getName(), jvmGenericType);
		types.put(jvmEnumerationType.getName(), jvmEnumerationType);
	}

	public void installProxyURI(URI resourceURI, EObject eobject, QualifiedName name) {
		if (resourceURI == null)
			throw new NullPointerException("resourceURI");
		if (eobject == null)
			throw new NullPointerException("eobject");
		if (name == null)
			throw new NullPointerException("name");
		URI proxyURI = getProxyURI(resourceURI, eobject, name);
		((InternalEObject) eobject).eSetProxyURI(proxyURI);
	}

	public URI getProxyURI(URI resourceURI, EObject eObject, QualifiedName name) {
		if (!isSupported(eObject)) {
			throw new IllegalArgumentException("eObjects of type " + eObject.eClass().getName() + " are not supported.");
		}
		return resourceURI.appendFragment(encodeFragment(eObject.eClass(), name));
	}

	private boolean isSupported(EObject eObject) {
		return types.containsValue(eObject.eClass());
	}

	public Pair<EClass, QualifiedName> decodeProxy(EObject obj) {
		if (obj != null) {
			URI proxyURI = ((InternalEObject) obj).eProxyURI();
			if (proxyURI != null) {
				return decodeProxyUri(proxyURI);
			}
		}
		throw new IllegalArgumentException("" + obj);
	}

	public Pair<EClass, QualifiedName> decodeProxyUri(URI proxyUri) {
		final String fragment = proxyUri.fragment();
		if (fragment != null) {
			Pair<EClass, QualifiedName> fragmentInfo = decodeFragment(fragment);
			if (fragmentInfo != null) {
				return fragmentInfo;
			}
		}
		throw new IllegalArgumentException("couldn't parse URI :'" + proxyUri);
	}

	private final static String DELIM = "-=-";

	public String encodeFragment(EClass eclass, QualifiedName name) {
		return eclass.getName() + DELIM + name.toString();
	}

	public Pair<EClass, QualifiedName> decodeFragment(String fragment) {
		String[] segments = fragment.split(DELIM);
		if (segments.length == 2) {
			String clazzName = segments[0];
			QualifiedName name = nameConverter.toQualifiedName(segments[1]);
			if (types.containsKey(clazzName)) {
				return Tuples.create(types.get(clazzName), name);
			}
		}
		return null;
	}

}
