/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package micronaut.kafka.avro.model;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class PartnerInfo extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 6317344446171557786L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PartnerInfo\",\"namespace\":\"micronaut.kafka.avro.model\",\"fields\":[{\"name\":\"partnerId\",\"type\":\"string\",\"doc\":\"Customer ID\"},{\"name\":\"email\",\"type\":\"string\",\"doc\":\"contact email address\"},{\"name\":\"telephone\",\"type\":\"string\",\"doc\":\"contact telephone number\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PartnerInfo> ENCODER =
      new BinaryMessageEncoder<PartnerInfo>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PartnerInfo> DECODER =
      new BinaryMessageDecoder<PartnerInfo>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<PartnerInfo> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<PartnerInfo> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<PartnerInfo> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PartnerInfo>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this PartnerInfo to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a PartnerInfo from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a PartnerInfo instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static PartnerInfo fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** Customer ID */
   private java.lang.CharSequence partnerId;
  /** contact email address */
   private java.lang.CharSequence email;
  /** contact telephone number */
   private java.lang.CharSequence telephone;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PartnerInfo() {}

  /**
   * All-args constructor.
   * @param partnerId Customer ID
   * @param email contact email address
   * @param telephone contact telephone number
   */
  public PartnerInfo(java.lang.CharSequence partnerId, java.lang.CharSequence email, java.lang.CharSequence telephone) {
    this.partnerId = partnerId;
    this.email = email;
    this.telephone = telephone;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return partnerId;
    case 1: return email;
    case 2: return telephone;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: partnerId = (java.lang.CharSequence)value$; break;
    case 1: email = (java.lang.CharSequence)value$; break;
    case 2: telephone = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'partnerId' field.
   * @return Customer ID
   */
  public java.lang.CharSequence getPartnerId() {
    return partnerId;
  }


  /**
   * Sets the value of the 'partnerId' field.
   * Customer ID
   * @param value the value to set.
   */
  public void setPartnerId(java.lang.CharSequence value) {
    this.partnerId = value;
  }

  /**
   * Gets the value of the 'email' field.
   * @return contact email address
   */
  public java.lang.CharSequence getEmail() {
    return email;
  }


  /**
   * Sets the value of the 'email' field.
   * contact email address
   * @param value the value to set.
   */
  public void setEmail(java.lang.CharSequence value) {
    this.email = value;
  }

  /**
   * Gets the value of the 'telephone' field.
   * @return contact telephone number
   */
  public java.lang.CharSequence getTelephone() {
    return telephone;
  }


  /**
   * Sets the value of the 'telephone' field.
   * contact telephone number
   * @param value the value to set.
   */
  public void setTelephone(java.lang.CharSequence value) {
    this.telephone = value;
  }

  /**
   * Creates a new PartnerInfo RecordBuilder.
   * @return A new PartnerInfo RecordBuilder
   */
  public static micronaut.kafka.avro.model.PartnerInfo.Builder newBuilder() {
    return new micronaut.kafka.avro.model.PartnerInfo.Builder();
  }

  /**
   * Creates a new PartnerInfo RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PartnerInfo RecordBuilder
   */
  public static micronaut.kafka.avro.model.PartnerInfo.Builder newBuilder(micronaut.kafka.avro.model.PartnerInfo.Builder other) {
    if (other == null) {
      return new micronaut.kafka.avro.model.PartnerInfo.Builder();
    } else {
      return new micronaut.kafka.avro.model.PartnerInfo.Builder(other);
    }
  }

  /**
   * Creates a new PartnerInfo RecordBuilder by copying an existing PartnerInfo instance.
   * @param other The existing instance to copy.
   * @return A new PartnerInfo RecordBuilder
   */
  public static micronaut.kafka.avro.model.PartnerInfo.Builder newBuilder(micronaut.kafka.avro.model.PartnerInfo other) {
    if (other == null) {
      return new micronaut.kafka.avro.model.PartnerInfo.Builder();
    } else {
      return new micronaut.kafka.avro.model.PartnerInfo.Builder(other);
    }
  }

  /**
   * RecordBuilder for PartnerInfo instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PartnerInfo>
    implements org.apache.avro.data.RecordBuilder<PartnerInfo> {

    /** Customer ID */
    private java.lang.CharSequence partnerId;
    /** contact email address */
    private java.lang.CharSequence email;
    /** contact telephone number */
    private java.lang.CharSequence telephone;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(micronaut.kafka.avro.model.PartnerInfo.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.partnerId)) {
        this.partnerId = data().deepCopy(fields()[0].schema(), other.partnerId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.email)) {
        this.email = data().deepCopy(fields()[1].schema(), other.email);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.telephone)) {
        this.telephone = data().deepCopy(fields()[2].schema(), other.telephone);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing PartnerInfo instance
     * @param other The existing instance to copy.
     */
    private Builder(micronaut.kafka.avro.model.PartnerInfo other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.partnerId)) {
        this.partnerId = data().deepCopy(fields()[0].schema(), other.partnerId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.email)) {
        this.email = data().deepCopy(fields()[1].schema(), other.email);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.telephone)) {
        this.telephone = data().deepCopy(fields()[2].schema(), other.telephone);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'partnerId' field.
      * Customer ID
      * @return The value.
      */
    public java.lang.CharSequence getPartnerId() {
      return partnerId;
    }


    /**
      * Sets the value of the 'partnerId' field.
      * Customer ID
      * @param value The value of 'partnerId'.
      * @return This builder.
      */
    public micronaut.kafka.avro.model.PartnerInfo.Builder setPartnerId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.partnerId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'partnerId' field has been set.
      * Customer ID
      * @return True if the 'partnerId' field has been set, false otherwise.
      */
    public boolean hasPartnerId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'partnerId' field.
      * Customer ID
      * @return This builder.
      */
    public micronaut.kafka.avro.model.PartnerInfo.Builder clearPartnerId() {
      partnerId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'email' field.
      * contact email address
      * @return The value.
      */
    public java.lang.CharSequence getEmail() {
      return email;
    }


    /**
      * Sets the value of the 'email' field.
      * contact email address
      * @param value The value of 'email'.
      * @return This builder.
      */
    public micronaut.kafka.avro.model.PartnerInfo.Builder setEmail(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.email = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'email' field has been set.
      * contact email address
      * @return True if the 'email' field has been set, false otherwise.
      */
    public boolean hasEmail() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'email' field.
      * contact email address
      * @return This builder.
      */
    public micronaut.kafka.avro.model.PartnerInfo.Builder clearEmail() {
      email = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'telephone' field.
      * contact telephone number
      * @return The value.
      */
    public java.lang.CharSequence getTelephone() {
      return telephone;
    }


    /**
      * Sets the value of the 'telephone' field.
      * contact telephone number
      * @param value The value of 'telephone'.
      * @return This builder.
      */
    public micronaut.kafka.avro.model.PartnerInfo.Builder setTelephone(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.telephone = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'telephone' field has been set.
      * contact telephone number
      * @return True if the 'telephone' field has been set, false otherwise.
      */
    public boolean hasTelephone() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'telephone' field.
      * contact telephone number
      * @return This builder.
      */
    public micronaut.kafka.avro.model.PartnerInfo.Builder clearTelephone() {
      telephone = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PartnerInfo build() {
      try {
        PartnerInfo record = new PartnerInfo();
        record.partnerId = fieldSetFlags()[0] ? this.partnerId : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.email = fieldSetFlags()[1] ? this.email : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.telephone = fieldSetFlags()[2] ? this.telephone : (java.lang.CharSequence) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PartnerInfo>
    WRITER$ = (org.apache.avro.io.DatumWriter<PartnerInfo>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PartnerInfo>
    READER$ = (org.apache.avro.io.DatumReader<PartnerInfo>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.partnerId);

    out.writeString(this.email);

    out.writeString(this.telephone);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.partnerId = in.readString(this.partnerId instanceof Utf8 ? (Utf8)this.partnerId : null);

      this.email = in.readString(this.email instanceof Utf8 ? (Utf8)this.email : null);

      this.telephone = in.readString(this.telephone instanceof Utf8 ? (Utf8)this.telephone : null);

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.partnerId = in.readString(this.partnerId instanceof Utf8 ? (Utf8)this.partnerId : null);
          break;

        case 1:
          this.email = in.readString(this.email instanceof Utf8 ? (Utf8)this.email : null);
          break;

        case 2:
          this.telephone = in.readString(this.telephone instanceof Utf8 ? (Utf8)this.telephone : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









