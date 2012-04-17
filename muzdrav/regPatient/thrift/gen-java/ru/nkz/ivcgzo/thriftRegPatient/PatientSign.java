/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package ru.nkz.ivcgzo.thriftRegPatient;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientSign implements org.apache.thrift.TBase<PatientSign, PatientSign._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PatientSign");

  private static final org.apache.thrift.protocol.TField NPASP_FIELD_DESC = new org.apache.thrift.protocol.TField("npasp", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField GRUP_FIELD_DESC = new org.apache.thrift.protocol.TField("grup", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField PH_FIELD_DESC = new org.apache.thrift.protocol.TField("ph", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField ALLERG_FIELD_DESC = new org.apache.thrift.protocol.TField("allerg", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField FARMKOL_FIELD_DESC = new org.apache.thrift.protocol.TField("farmkol", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField VITAE_FIELD_DESC = new org.apache.thrift.protocol.TField("vitae", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField VRED_FIELD_DESC = new org.apache.thrift.protocol.TField("vred", org.apache.thrift.protocol.TType.STRING, (short)7);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new PatientSignStandardSchemeFactory());
    schemes.put(TupleScheme.class, new PatientSignTupleSchemeFactory());
  }

  public int npasp; // required
  public String grup; // required
  public String ph; // required
  public String allerg; // required
  public String farmkol; // required
  public String vitae; // required
  public String vred; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NPASP((short)1, "npasp"),
    GRUP((short)2, "grup"),
    PH((short)3, "ph"),
    ALLERG((short)4, "allerg"),
    FARMKOL((short)5, "farmkol"),
    VITAE((short)6, "vitae"),
    VRED((short)7, "vred");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NPASP
          return NPASP;
        case 2: // GRUP
          return GRUP;
        case 3: // PH
          return PH;
        case 4: // ALLERG
          return ALLERG;
        case 5: // FARMKOL
          return FARMKOL;
        case 6: // VITAE
          return VITAE;
        case 7: // VRED
          return VRED;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __NPASP_ISSET_ID = 0;
  private BitSet __isset_bit_vector = new BitSet(1);
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NPASP, new org.apache.thrift.meta_data.FieldMetaData("npasp", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.GRUP, new org.apache.thrift.meta_data.FieldMetaData("grup", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PH, new org.apache.thrift.meta_data.FieldMetaData("ph", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ALLERG, new org.apache.thrift.meta_data.FieldMetaData("allerg", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.FARMKOL, new org.apache.thrift.meta_data.FieldMetaData("farmkol", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VITAE, new org.apache.thrift.meta_data.FieldMetaData("vitae", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VRED, new org.apache.thrift.meta_data.FieldMetaData("vred", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PatientSign.class, metaDataMap);
  }

  public PatientSign() {
  }

  public PatientSign(
    int npasp,
    String grup,
    String ph,
    String allerg,
    String farmkol,
    String vitae,
    String vred)
  {
    this();
    this.npasp = npasp;
    setNpaspIsSet(true);
    this.grup = grup;
    this.ph = ph;
    this.allerg = allerg;
    this.farmkol = farmkol;
    this.vitae = vitae;
    this.vred = vred;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PatientSign(PatientSign other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    this.npasp = other.npasp;
    if (other.isSetGrup()) {
      this.grup = other.grup;
    }
    if (other.isSetPh()) {
      this.ph = other.ph;
    }
    if (other.isSetAllerg()) {
      this.allerg = other.allerg;
    }
    if (other.isSetFarmkol()) {
      this.farmkol = other.farmkol;
    }
    if (other.isSetVitae()) {
      this.vitae = other.vitae;
    }
    if (other.isSetVred()) {
      this.vred = other.vred;
    }
  }

  public PatientSign deepCopy() {
    return new PatientSign(this);
  }

  @Override
  public void clear() {
    setNpaspIsSet(false);
    this.npasp = 0;
    this.grup = null;
    this.ph = null;
    this.allerg = null;
    this.farmkol = null;
    this.vitae = null;
    this.vred = null;
  }

  public int getNpasp() {
    return this.npasp;
  }

  public PatientSign setNpasp(int npasp) {
    this.npasp = npasp;
    setNpaspIsSet(true);
    return this;
  }

  public void unsetNpasp() {
    __isset_bit_vector.clear(__NPASP_ISSET_ID);
  }

  /** Returns true if field npasp is set (has been assigned a value) and false otherwise */
  public boolean isSetNpasp() {
    return __isset_bit_vector.get(__NPASP_ISSET_ID);
  }

  public void setNpaspIsSet(boolean value) {
    __isset_bit_vector.set(__NPASP_ISSET_ID, value);
  }

  public String getGrup() {
    return this.grup;
  }

  public PatientSign setGrup(String grup) {
    this.grup = grup;
    return this;
  }

  public void unsetGrup() {
    this.grup = null;
  }

  /** Returns true if field grup is set (has been assigned a value) and false otherwise */
  public boolean isSetGrup() {
    return this.grup != null;
  }

  public void setGrupIsSet(boolean value) {
    if (!value) {
      this.grup = null;
    }
  }

  public String getPh() {
    return this.ph;
  }

  public PatientSign setPh(String ph) {
    this.ph = ph;
    return this;
  }

  public void unsetPh() {
    this.ph = null;
  }

  /** Returns true if field ph is set (has been assigned a value) and false otherwise */
  public boolean isSetPh() {
    return this.ph != null;
  }

  public void setPhIsSet(boolean value) {
    if (!value) {
      this.ph = null;
    }
  }

  public String getAllerg() {
    return this.allerg;
  }

  public PatientSign setAllerg(String allerg) {
    this.allerg = allerg;
    return this;
  }

  public void unsetAllerg() {
    this.allerg = null;
  }

  /** Returns true if field allerg is set (has been assigned a value) and false otherwise */
  public boolean isSetAllerg() {
    return this.allerg != null;
  }

  public void setAllergIsSet(boolean value) {
    if (!value) {
      this.allerg = null;
    }
  }

  public String getFarmkol() {
    return this.farmkol;
  }

  public PatientSign setFarmkol(String farmkol) {
    this.farmkol = farmkol;
    return this;
  }

  public void unsetFarmkol() {
    this.farmkol = null;
  }

  /** Returns true if field farmkol is set (has been assigned a value) and false otherwise */
  public boolean isSetFarmkol() {
    return this.farmkol != null;
  }

  public void setFarmkolIsSet(boolean value) {
    if (!value) {
      this.farmkol = null;
    }
  }

  public String getVitae() {
    return this.vitae;
  }

  public PatientSign setVitae(String vitae) {
    this.vitae = vitae;
    return this;
  }

  public void unsetVitae() {
    this.vitae = null;
  }

  /** Returns true if field vitae is set (has been assigned a value) and false otherwise */
  public boolean isSetVitae() {
    return this.vitae != null;
  }

  public void setVitaeIsSet(boolean value) {
    if (!value) {
      this.vitae = null;
    }
  }

  public String getVred() {
    return this.vred;
  }

  public PatientSign setVred(String vred) {
    this.vred = vred;
    return this;
  }

  public void unsetVred() {
    this.vred = null;
  }

  /** Returns true if field vred is set (has been assigned a value) and false otherwise */
  public boolean isSetVred() {
    return this.vred != null;
  }

  public void setVredIsSet(boolean value) {
    if (!value) {
      this.vred = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NPASP:
      if (value == null) {
        unsetNpasp();
      } else {
        setNpasp((Integer)value);
      }
      break;

    case GRUP:
      if (value == null) {
        unsetGrup();
      } else {
        setGrup((String)value);
      }
      break;

    case PH:
      if (value == null) {
        unsetPh();
      } else {
        setPh((String)value);
      }
      break;

    case ALLERG:
      if (value == null) {
        unsetAllerg();
      } else {
        setAllerg((String)value);
      }
      break;

    case FARMKOL:
      if (value == null) {
        unsetFarmkol();
      } else {
        setFarmkol((String)value);
      }
      break;

    case VITAE:
      if (value == null) {
        unsetVitae();
      } else {
        setVitae((String)value);
      }
      break;

    case VRED:
      if (value == null) {
        unsetVred();
      } else {
        setVred((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NPASP:
      return Integer.valueOf(getNpasp());

    case GRUP:
      return getGrup();

    case PH:
      return getPh();

    case ALLERG:
      return getAllerg();

    case FARMKOL:
      return getFarmkol();

    case VITAE:
      return getVitae();

    case VRED:
      return getVred();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NPASP:
      return isSetNpasp();
    case GRUP:
      return isSetGrup();
    case PH:
      return isSetPh();
    case ALLERG:
      return isSetAllerg();
    case FARMKOL:
      return isSetFarmkol();
    case VITAE:
      return isSetVitae();
    case VRED:
      return isSetVred();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof PatientSign)
      return this.equals((PatientSign)that);
    return false;
  }

  public boolean equals(PatientSign that) {
    if (that == null)
      return false;

    boolean this_present_npasp = true;
    boolean that_present_npasp = true;
    if (this_present_npasp || that_present_npasp) {
      if (!(this_present_npasp && that_present_npasp))
        return false;
      if (this.npasp != that.npasp)
        return false;
    }

    boolean this_present_grup = true && this.isSetGrup();
    boolean that_present_grup = true && that.isSetGrup();
    if (this_present_grup || that_present_grup) {
      if (!(this_present_grup && that_present_grup))
        return false;
      if (!this.grup.equals(that.grup))
        return false;
    }

    boolean this_present_ph = true && this.isSetPh();
    boolean that_present_ph = true && that.isSetPh();
    if (this_present_ph || that_present_ph) {
      if (!(this_present_ph && that_present_ph))
        return false;
      if (!this.ph.equals(that.ph))
        return false;
    }

    boolean this_present_allerg = true && this.isSetAllerg();
    boolean that_present_allerg = true && that.isSetAllerg();
    if (this_present_allerg || that_present_allerg) {
      if (!(this_present_allerg && that_present_allerg))
        return false;
      if (!this.allerg.equals(that.allerg))
        return false;
    }

    boolean this_present_farmkol = true && this.isSetFarmkol();
    boolean that_present_farmkol = true && that.isSetFarmkol();
    if (this_present_farmkol || that_present_farmkol) {
      if (!(this_present_farmkol && that_present_farmkol))
        return false;
      if (!this.farmkol.equals(that.farmkol))
        return false;
    }

    boolean this_present_vitae = true && this.isSetVitae();
    boolean that_present_vitae = true && that.isSetVitae();
    if (this_present_vitae || that_present_vitae) {
      if (!(this_present_vitae && that_present_vitae))
        return false;
      if (!this.vitae.equals(that.vitae))
        return false;
    }

    boolean this_present_vred = true && this.isSetVred();
    boolean that_present_vred = true && that.isSetVred();
    if (this_present_vred || that_present_vred) {
      if (!(this_present_vred && that_present_vred))
        return false;
      if (!this.vred.equals(that.vred))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(PatientSign other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    PatientSign typedOther = (PatientSign)other;

    lastComparison = Boolean.valueOf(isSetNpasp()).compareTo(typedOther.isSetNpasp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNpasp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.npasp, typedOther.npasp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetGrup()).compareTo(typedOther.isSetGrup());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGrup()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.grup, typedOther.grup);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPh()).compareTo(typedOther.isSetPh());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPh()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ph, typedOther.ph);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAllerg()).compareTo(typedOther.isSetAllerg());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAllerg()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.allerg, typedOther.allerg);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFarmkol()).compareTo(typedOther.isSetFarmkol());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFarmkol()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.farmkol, typedOther.farmkol);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVitae()).compareTo(typedOther.isSetVitae());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVitae()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.vitae, typedOther.vitae);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVred()).compareTo(typedOther.isSetVred());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVred()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.vred, typedOther.vred);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("PatientSign(");
    boolean first = true;

    sb.append("npasp:");
    sb.append(this.npasp);
    first = false;
    if (!first) sb.append(", ");
    sb.append("grup:");
    if (this.grup == null) {
      sb.append("null");
    } else {
      sb.append(this.grup);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("ph:");
    if (this.ph == null) {
      sb.append("null");
    } else {
      sb.append(this.ph);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("allerg:");
    if (this.allerg == null) {
      sb.append("null");
    } else {
      sb.append(this.allerg);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("farmkol:");
    if (this.farmkol == null) {
      sb.append("null");
    } else {
      sb.append(this.farmkol);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("vitae:");
    if (this.vitae == null) {
      sb.append("null");
    } else {
      sb.append(this.vitae);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("vred:");
    if (this.vred == null) {
      sb.append("null");
    } else {
      sb.append(this.vred);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bit_vector = new BitSet(1);
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class PatientSignStandardSchemeFactory implements SchemeFactory {
    public PatientSignStandardScheme getScheme() {
      return new PatientSignStandardScheme();
    }
  }

  private static class PatientSignStandardScheme extends StandardScheme<PatientSign> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, PatientSign struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NPASP
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.npasp = iprot.readI32();
              struct.setNpaspIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // GRUP
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.grup = iprot.readString();
              struct.setGrupIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ph = iprot.readString();
              struct.setPhIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // ALLERG
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.allerg = iprot.readString();
              struct.setAllergIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // FARMKOL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.farmkol = iprot.readString();
              struct.setFarmkolIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // VITAE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.vitae = iprot.readString();
              struct.setVitaeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // VRED
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.vred = iprot.readString();
              struct.setVredIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, PatientSign struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(NPASP_FIELD_DESC);
      oprot.writeI32(struct.npasp);
      oprot.writeFieldEnd();
      if (struct.grup != null) {
        oprot.writeFieldBegin(GRUP_FIELD_DESC);
        oprot.writeString(struct.grup);
        oprot.writeFieldEnd();
      }
      if (struct.ph != null) {
        oprot.writeFieldBegin(PH_FIELD_DESC);
        oprot.writeString(struct.ph);
        oprot.writeFieldEnd();
      }
      if (struct.allerg != null) {
        oprot.writeFieldBegin(ALLERG_FIELD_DESC);
        oprot.writeString(struct.allerg);
        oprot.writeFieldEnd();
      }
      if (struct.farmkol != null) {
        oprot.writeFieldBegin(FARMKOL_FIELD_DESC);
        oprot.writeString(struct.farmkol);
        oprot.writeFieldEnd();
      }
      if (struct.vitae != null) {
        oprot.writeFieldBegin(VITAE_FIELD_DESC);
        oprot.writeString(struct.vitae);
        oprot.writeFieldEnd();
      }
      if (struct.vred != null) {
        oprot.writeFieldBegin(VRED_FIELD_DESC);
        oprot.writeString(struct.vred);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PatientSignTupleSchemeFactory implements SchemeFactory {
    public PatientSignTupleScheme getScheme() {
      return new PatientSignTupleScheme();
    }
  }

  private static class PatientSignTupleScheme extends TupleScheme<PatientSign> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, PatientSign struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetNpasp()) {
        optionals.set(0);
      }
      if (struct.isSetGrup()) {
        optionals.set(1);
      }
      if (struct.isSetPh()) {
        optionals.set(2);
      }
      if (struct.isSetAllerg()) {
        optionals.set(3);
      }
      if (struct.isSetFarmkol()) {
        optionals.set(4);
      }
      if (struct.isSetVitae()) {
        optionals.set(5);
      }
      if (struct.isSetVred()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetNpasp()) {
        oprot.writeI32(struct.npasp);
      }
      if (struct.isSetGrup()) {
        oprot.writeString(struct.grup);
      }
      if (struct.isSetPh()) {
        oprot.writeString(struct.ph);
      }
      if (struct.isSetAllerg()) {
        oprot.writeString(struct.allerg);
      }
      if (struct.isSetFarmkol()) {
        oprot.writeString(struct.farmkol);
      }
      if (struct.isSetVitae()) {
        oprot.writeString(struct.vitae);
      }
      if (struct.isSetVred()) {
        oprot.writeString(struct.vred);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PatientSign struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.npasp = iprot.readI32();
        struct.setNpaspIsSet(true);
      }
      if (incoming.get(1)) {
        struct.grup = iprot.readString();
        struct.setGrupIsSet(true);
      }
      if (incoming.get(2)) {
        struct.ph = iprot.readString();
        struct.setPhIsSet(true);
      }
      if (incoming.get(3)) {
        struct.allerg = iprot.readString();
        struct.setAllergIsSet(true);
      }
      if (incoming.get(4)) {
        struct.farmkol = iprot.readString();
        struct.setFarmkolIsSet(true);
      }
      if (incoming.get(5)) {
        struct.vitae = iprot.readString();
        struct.setVitaeIsSet(true);
      }
      if (incoming.get(6)) {
        struct.vred = iprot.readString();
        struct.setVredIsSet(true);
      }
    }
  }

}

