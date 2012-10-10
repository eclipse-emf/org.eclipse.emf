/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.lib;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.MapExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure3;

public class XcoreEMapExtensions
{
  /**
   * Applies the given the procedure to each {@link java.util.Map.Entry key-value pair} of the given map.
   * The procedure takes the key and the value as arguments.
   *
   * @param map the map. May not be <code>null</code>.
   * @param procedure the procedure. May not be <code>null</code>.
   */
  public static <K, V> void forEach(EMap<K, V> map, Procedure2<? super K, ? super V> procedure)
  {
    MapExtensions.forEach(map.map(), procedure);
  }

  /**
   * Applies the given the procedure to each {@link java.util.Map.Entry key-value pair} of the given map.
   * The procedure takes the key, the value, and a loop counter as arguments.
   * If the counter would overflow,
   * {@link Integer#MAX_VALUE} is returned for all subsequent pairs. 
   * The first pair is at index zero.
   *
   * @param map the map. May not be <code>null</code>.
   * @param procedure the procedure. May not be <code>null</code>.
   */
  public static <K, V> void forEach(EMap<K, V> map, Procedure3<? super K, ? super V, ? super Integer> procedure) 
  {
    MapExtensions.forEach(map.map(), procedure);
  }

  /**
   * Returns a filtered view of the map.
   * Changes to one affect the other.
   * The mapping is done lazily, i.e.,
   * subsequent access to the values in the view will repeatedly apply the predicate.
   * Characteristics of the map, such as iteration order, are left intact.
   * Changes in the map are reflected in the view.
   * The view supports removal, if the map supports removal.
   *
   * @param map the original map. May not be <code>null</code>.
   * @param predicate the predicate for filtering. May not be <code>null</code>.
   * @return a filtered view the map. Never <code>null</code>.
   */
  public static <K, V> EMap<K, V> filter(EMap<K, V> map, final Function2<? super K, ? super V, Boolean> predicate)
  {
    return ECollections.asEMap(MapExtensions.filter(map.map(), predicate));
  }

  /**
   * Returns a transformed view of the map that applies the given the transformation to each value of map.
   * The mapping is done lazily, i.e.,
   * each access of a value in the map will repeatedly apply the transformation. 
   * Characteristics of the map, such as iteration order, are preserved. 
   * Changes to the original map are reflected in the view. 
   * The view supports removal, if the map supports removal.</p>
   * 
   * @param map the map to be transformed. May not be <code>null</code>.
   * @param transformation the transformation to apply. May not be <code>null</code>.
   * @return a map with the same keys but with transformed values. Never <code>null</code>.
   */
  public static <K, V1, V2> EMap<K, V2> mapValues(EMap<K, V1> map, Function1<? super V1, V2> transformation) 
  {
    return ECollections.asEMap(MapExtensions.mapValues(map.map(), transformation));
  }
}
