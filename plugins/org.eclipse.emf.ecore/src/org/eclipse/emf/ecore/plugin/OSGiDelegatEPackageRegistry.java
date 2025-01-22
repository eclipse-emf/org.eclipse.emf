/**
 * Copyright (c) 2012 - 2025 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.eclipse.emf.ecore.plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * A {@link Registry} that tries to look for a service as a global Registry. Due
 * to the dynamic nature of OSGi Services and the static nature of the default
 * {@link Registry} of {@link Registry#INSTANCE} it provides an internal backup.
 * Which is used until the service comes around. All write operations will be
 * stored with the backup as well, so it can take up its role, when the service
 * goes away. When a suitable Service comes around, all statically added
 * {@link EPackage}s will be added to the incoming Registry.
 * 
 * Only {@link Registry} Services with the property
 * "emf.default.epackage.registry=true" will be considered. If multiple match,
 * the one with the highest service rank or if non is present, the first one
 * wins.
 */
public class OSGiDelegatEPackageRegistry implements Registry, ServiceTrackerCustomizer<EPackage.Registry, Object> {

  public static Filter FILTER = null;
  static {
    try {
      FILTER = FrameworkUtil.createFilter(
          "(&(" + Constants.OBJECTCLASS + "=" + Registry.class.getName() + ")(emf.default.epackage.registry=true))");
    } catch (Exception e) {
      // Must not happen
    }
  }

  private final Registry backup = new EPackageRegistryImpl();
  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  private final List<ServiceReference<Registry>> knownReferences = new ArrayList<>();

  private ServiceReference<Registry> currentDelegateRef = null;
  private Registry delegate = null;

  private BundleContext context;

  /**
   * Sets the BundleContext to use. Must be set before the Servicetracker is
   * opened.
   * 
   * @param context the context to set
   */
  public BundleContext getBundleContext() {
    if (context == null) {
      context = FrameworkUtil.getBundle(getClass()).getBundleContext();
    }
    return this.context;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map#size()
   */
  @Override
  public int size() {
    lock.readLock().lock();
    try {
      if (delegate == null) {
        return backup.size();
      } else {
        return delegate.size();
      }
    } finally {
      lock.readLock().unlock();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map#isEmpty()
   */
  @Override
  public boolean isEmpty() {
    lock.readLock().lock();
    try {
      if (delegate == null) {
        return backup.isEmpty();
      } else {
        return delegate.isEmpty();
      }
    } finally {
      lock.readLock().unlock();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map#containsKey(java.lang.Object)
   */
  @Override
  public boolean containsKey(Object key) {
    lock.readLock().lock();
    try {
      if (delegate == null) {
        return backup.containsKey(key);
      } else {
        return delegate.containsKey(key);
      }
    } finally {
      lock.readLock().unlock();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map#containsValue(java.lang.Object)
   */
  @Override
  public boolean containsValue(Object value) {
    lock.readLock().lock();
    try {
      if (delegate == null) {
        return backup.containsValue(value);
      } else {
        return delegate.containsValue(value);
      }
    } finally {
      lock.readLock().unlock();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map#get(java.lang.Object)
   */
  @Override
  public Object get(Object key) {
    lock.readLock().lock();
    try {
      if (delegate == null) {
        return backup.get(key);
      } else {
        return delegate.get(key);
      }
    } finally {
      lock.readLock().unlock();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map#put(java.lang.Object, java.lang.Object)
   */
  @Override
  public Object put(String key, Object value) {
    lock.readLock().lock();
    try {
      if (delegate != null) {
        backup.put(key, value);
        return delegate.put(key, value);
      }
      return backup.put(key, value);
    } finally {
      lock.readLock().unlock();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map#remove(java.lang.Object)
   */
  @Override
  public Object remove(Object key) {
    lock.readLock().lock();
    try {
      if (delegate != null) {
        backup.remove(key);
        return delegate.remove(key);
      }
      return backup.remove(key);
    } finally {
      lock.readLock().unlock();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map#putAll(java.util.Map)
   */
  @Override
  public void putAll(Map<? extends String, ? extends Object> m) {
    lock.readLock().lock();
    try {
      if (delegate == null) {
        backup.putAll(m);
      } else {
        delegate.putAll(m);
      }
    } finally {
      lock.readLock().unlock();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map#clear()
   */
  @Override
  public void clear() {
    lock.readLock().lock();
    try {
      if (delegate == null) {
        backup.clear();
      } else {
        delegate.clear();
      }
    } finally {
      lock.readLock().unlock();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map#keySet()
   */
  @Override
  public Set<String> keySet() {
    lock.readLock().lock();
    try {
      if (delegate == null) {
        return backup.keySet();
      } else {
        return delegate.keySet();
      }
    } finally {
      lock.readLock().unlock();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map#values()
   */
  @Override
  public Collection<Object> values() {
    lock.readLock().lock();
    try {
      if (delegate == null) {
        return backup.values();
      } else {
        return delegate.values();
      }
    } finally {
      lock.readLock().unlock();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map#entrySet()
   */
  @Override
  public Set<Entry<String, Object>> entrySet() {
    lock.readLock().lock();
    try {
      if (delegate == null) {
        return backup.entrySet();
      } else {
        return delegate.entrySet();
      }
    } finally {
      lock.readLock().unlock();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.EPackage.Registry#getEPackage(java.lang.String)
   */
  @Override
  public EPackage getEPackage(String nsURI) {
    lock.readLock().lock();
    try {
      if (delegate == null) {
        return backup.getEPackage(nsURI);
      } else {
        return delegate.getEPackage(nsURI);
      }
    } finally {
      lock.readLock().unlock();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.EPackage.Registry#getEFactory(java.lang.String)
   */
  @Override
  public EFactory getEFactory(String nsURI) {
    lock.readLock().lock();
    try {
      if (delegate == null) {
        return backup.getEFactory(nsURI);
      } else {
        return delegate.getEFactory(nsURI);
      }
    } finally {
      lock.readLock().unlock();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.osgi.util.tracker.ServiceTrackerCustomizer#addingService(org.osgi.
   * framework.ServiceReference)
   */
  @Override
  public Object addingService(ServiceReference<Registry> reference) {
    knownReferences.add(reference);
    handleDelegateRegistryChange();
    return new Object();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.osgi.util.tracker.ServiceTrackerCustomizer#modifiedService(org.osgi.
   * framework.ServiceReference, java.lang.Object)
   */
  @Override
  public void modifiedService(ServiceReference<Registry> reference, Object service) {
    handleDelegateRegistryChange();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.osgi.util.tracker.ServiceTrackerCustomizer#removedService(org.osgi.
   * framework.ServiceReference, java.lang.Object)
   */
  @Override
  public void removedService(ServiceReference<Registry> reference, Object service) {
    knownReferences.remove(reference);
    handleDelegateRegistryChange();

  }

  private void handleDelegateRegistryChange() {
    Collections.sort(knownReferences,
        Comparator.comparingInt(this::getServiceRank).thenComparing(Comparator.reverseOrder()));
    Collections.reverse(knownReferences);
    if (knownReferences.isEmpty() && delegate != null) {
      unsetDelegateSafe();
      return;
    }
    ServiceReference<Registry> reference = knownReferences.get(0);
    if (!reference.equals(currentDelegateRef)) {
      handleDelegateUpdate(reference);
    }
  }

  private void handleDelegateUpdate(ServiceReference<Registry> newReference) {
    Registry newDelegate = getBundleContext().getService(newReference);
    if (newDelegate != null) {
      lock.writeLock().lock();
      try {
        unsetDelegate();
        currentDelegateRef = newReference;
        delegate = newDelegate;
        newDelegate.putAll(backup);
      } finally {
        lock.writeLock().unlock();
      }
    }
  }

  private void unsetDelegateSafe() {
    lock.writeLock().lock();
    try {
      unsetDelegate();
    } finally {
      lock.writeLock().unlock();
    }
  }

  private void unsetDelegate() {
    if (currentDelegateRef != null) {
      getBundleContext().ungetService(currentDelegateRef);
      currentDelegateRef = null;
      delegate = null;
    }
  }

  private int getServiceRank(ServiceReference<Registry> s) {
    Object sr = s.getProperty(Constants.SERVICE_RANKING);
    if (sr != null && sr instanceof Integer) {
      return (Integer) sr;
    } else {
      return Integer.valueOf(0);
    }
  }
}