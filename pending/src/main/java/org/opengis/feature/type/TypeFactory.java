package org.opengis.feature.type;

import java.util.List;
import java.util.Set;

import org.opengis.feature.AttributeName;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * Factory interface for the typing system.
 * 
 * @author Gabriel Roldan, Axios Engineering TODO: add FeatureCollection factory
 *         methods
 */
public interface TypeFactory {

	/**
	 * 
	 * @param name
	 * @param binding
	 */
	AttributeType createType(String name, Class binding);

	/**
	 * 
	 * @param name
	 * @param binding
	 */
	AttributeType createType(AttributeName name, Class binding);

	/**
	 * 
	 * @param name
	 * @param binding
	 * @param identified
	 * @param nillable
	 * @param restrictions
	 */
	AttributeType createType(AttributeName name, Class binding, boolean identified,
			boolean nillable, Set<Filter> restrictions);

	/**
	 * 
	 * @param name
	 * @param binding
	 * @param identified
	 * @param nillable
	 * @param restrictions
	 * @param superType
	 */
	AttributeType createType(AttributeName name, Class binding, boolean identified,
			boolean nillable, Set<Filter> restrictions, AttributeType superType);

	GeometryType createGeometryType(AttributeName name, Class binding,
			boolean nillable, CoordinateReferenceSystem crs);

	GeometryType createGeometryType(AttributeName name, Class binding,
			boolean identified, boolean nillable,
			CoordinateReferenceSystem crs, Set<Filter> restrictions,
			GeometryType superType);

	/**
	 * 
	 * @param name
	 * @param schema
	 */
	ComplexType createType(String name, AttributeDescriptor schema);

	/**
	 * 
	 * @param name
	 * @param schema
	 */
	ComplexType createType(AttributeName name, AttributeDescriptor schema);

	/**
	 * 
	 * @param name
	 * @param schema
	 * @param identified
	 * @param nillable
	 * @param restrictions
	 */
	ComplexType createType(AttributeName name, AttributeDescriptor schema, boolean identified,
			boolean nillable, Set<Filter> restrictions);

	/**
	 * 
	 * @param name
	 * @param schema
	 * @param identified
	 * @param nillable
	 * @param restrictions
	 * @param superType
	 * @param isAbstract
	 */
	ComplexType createType(AttributeName name, AttributeDescriptor schema, boolean identified,
			boolean nillable, Set<Filter> restrictions, ComplexType superType,
			boolean isAbstract);

	/**
	 * 
	 * @param name
	 * @param schema
	 * @param defaultGeometry
	 */
	FeatureType createFeatureType(String name, AttributeDescriptor schema,
			GeometryType defaultGeometry);

	/**
	 * 
	 * @param name
	 * @param schema
	 * @param defaultGeometry
	 */
	FeatureType createFeatureType(AttributeName name, AttributeDescriptor schema,
			GeometryType defaultGeometry);

	/**
	 * 
	 * @param name
	 * @param schema
	 * @param defaultGeometry
	 * @param restrictions
	 * @param superType
	 * @param isAbstract
	 */
	FeatureType createFeatureType(AttributeName name, AttributeDescriptor schema,
			GeometryType defaultGeometry, Set<Filter> restrictions,
			FeatureType superType, boolean isAbstract);

	SimpleFeatureType createFeatureType(String name, List<AttributeType> types,
			GeometryType defaultGeometry);

	/**
	 * 
	 * @param name
	 * @param types
	 * @param defaultGeometry
	 */
	SimpleFeatureType createFeatureType(AttributeName name, List<AttributeType> types,
			GeometryType defaultGeometry);

	SimpleFeatureType createFeatureType(AttributeName name, List<AttributeType> types,
			GeometryType defaultGeometry, Set<Filter> restrictions,
			SimpleFeatureType superType, boolean isAbstract);

	public FeatureCollectionType createFeatureCollectionType();

	public FeatureCollectionType createFeatureCollectionType(
			FeatureType membersType);

	/**
	 * Creates a FeatureCollectionType named <code>name</code> whose member
	 * Features can be of any FeatureType.
	 * 
	 * @param name
	 */
	public FeatureCollectionType createFeatureCollectionType(AttributeName name);

	/**
	 * Creates a FeatureCollectionType named <code>name</code> whose member
	 * Features can be only of <code>membersType</code> FeatureType.
	 * 
	 * @param name
	 * @param membersType
	 */
	public FeatureCollectionType createFeatureCollectionType(AttributeName name,
			FeatureType membersType);

	/**
	 * Creates a FeatureCollectionType named <code>name</code> whose member
	 * Features can be of any of the FeatureTypes in <code>membersTypes</code>.
	 * <p>
	 * All parametesr may be null, in which case sensible defaults will be
	 * applied.
	 * </p>
	 * 
	 * @param name
	 *            name of FeatureCollectionType. Required if
	 *            <code>schema != null</code>. Otherwise, if
	 *            <code>null</code> is passed,
	 *            <code>gml:FeatureCollection</code> will be used.
	 * @param membersTypes
	 *            list of allowable FeatureTypes that Feature members must
	 *            adhere to.
	 * @param schema
	 *            the schema for the Feature representation of the collection.
	 *            You will generally pass <code>null</code>, at least you
	 *            want to add attributes to the FeatureCollection itself.
	 * @param defaultGeom
	 *            only needed if adding attributes to the Feature aspect of the
	 *            collection. Use <code>null</code> if you don't add
	 *            GeometryTypes or are just adding one, in which case it will be
	 *            used as the default geometry.
	 * @param restrictions
	 *            restrictions applied to contained Features.
	 * @param superType
	 *            the FeatureCollectionType the created one inherits from.
	 * @param isAbstract
	 *            wether the created FeatureCollectionType is abstract.
	 */
	public FeatureCollectionType createFeatureCollectionType(AttributeName name,
			Set<FeatureType> membersTypes, AttributeDescriptor schema,
			GeometryType defaultGeom, Set<Filter> restrictions,
			FeatureCollectionType superType, boolean isAbstract);
}