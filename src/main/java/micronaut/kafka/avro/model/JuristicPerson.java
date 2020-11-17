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

/** Partner such as company or organisation */
@org.apache.avro.specific.AvroGenerated
public class JuristicPerson extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 1174856839723238860L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"JuristicPerson\",\"namespace\":\"micronaut.kafka.avro.model\",\"doc\":\"Partner such as company or organisation\",\"fields\":[{\"name\":\"type\",\"type\":\"string\",\"doc\":\"type of person i.e. NaturalPerson or JuristicPerson\"},{\"name\":\"name\",\"type\":\"string\"}],\"version\":\"1\"}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<JuristicPerson> ENCODER =
      new BinaryMessageEncoder<JuristicPerson>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<JuristicPerson> DECODER =
      new BinaryMessageDecoder<JuristicPerson>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<JuristicPerson> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<JuristicPerson> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<JuristicPerson> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<JuristicPerson>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this JuristicPerson to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a JuristicPerson from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a JuristicPerson instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static JuristicPerson fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** type of person i.e. NaturalPerson or JuristicPerson */
   private java.lang.CharSequence type;
   private java.lang.CharSequence name;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public JuristicPerson() {}

  /**
   * All-args constructor.
   * @param type type of person i.e. NaturalPerson or JuristicPerson
   * @param name The new value for name
   */
  public JuristicPerson(java.lang.CharSequence type, java.lang.CharSequence name) {
    this.type = type;
    this.name = name;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return type;
    case 1: return name;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: type = (java.lang.CharSequence)value$; break;
    case 1: name = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'type' field.
   * @return type of person i.e. NaturalPerson or JuristicPerson
   */
  public java.lang.CharSequence getType() {
    return type;
  }


  /**
   * Sets the value of the 'type' field.
   * type of person i.e. NaturalPerson or JuristicPerson
   * @param value the value to set.
   */
  public void setType(java.lang.CharSequence value) {
    this.type = value;
  }

  /**
   * Gets the value of the 'name' field.
   * @return The value of the 'name' field.
   */
  public java.lang.CharSequence getName() {
    return name;
  }


  /**
   * Sets the value of the 'name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.name = value;
  }

  /**
   * Creates a new JuristicPerson RecordBuilder.
   * @return A new JuristicPerson RecordBuilder
   */
  public static micronaut.kafka.avro.model.JuristicPerson.Builder newBuilder() {
    return new micronaut.kafka.avro.model.JuristicPerson.Builder();
  }

  /**
   * Creates a new JuristicPerson RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new JuristicPerson RecordBuilder
   */
  public static micronaut.kafka.avro.model.JuristicPerson.Builder newBuilder(micronaut.kafka.avro.model.JuristicPerson.Builder other) {
    if (other == null) {
      return new micronaut.kafka.avro.model.JuristicPerson.Builder();
    } else {
      return new micronaut.kafka.avro.model.JuristicPerson.Builder(other);
    }
  }

  /**
   * Creates a new JuristicPerson RecordBuilder by copying an existing JuristicPerson instance.
   * @param other The existing instance to copy.
   * @return A new JuristicPerson RecordBuilder
   */
  public static micronaut.kafka.avro.model.JuristicPerson.Builder newBuilder(micronaut.kafka.avro.model.JuristicPerson other) {
    if (other == null) {
      return new micronaut.kafka.avro.model.JuristicPerson.Builder();
    } else {
      return new micronaut.kafka.avro.model.JuristicPerson.Builder(other);
    }
  }

  /**
   * RecordBuilder for JuristicPerson instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<JuristicPerson>
    implements org.apache.avro.data.RecordBuilder<JuristicPerson> {

    /** type of person i.e. NaturalPerson or JuristicPerson */
    private java.lang.CharSequence type;
    private java.lang.CharSequence name;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(micronaut.kafka.avro.model.JuristicPerson.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.type)) {
        this.type = data().deepCopy(fields()[0].schema(), other.type);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing JuristicPerson instance
     * @param other The existing instance to copy.
     */
    private Builder(micronaut.kafka.avro.model.JuristicPerson other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.type)) {
        this.type = data().deepCopy(fields()[0].schema(), other.type);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'type' field.
      * type of person i.e. NaturalPerson or JuristicPerson
      * @return The value.
      */
    public java.lang.CharSequence getType() {
      return type;
    }


    /**
      * Sets the value of the 'type' field.
      * type of person i.e. NaturalPerson or JuristicPerson
      * @param value The value of 'type'.
      * @return This builder.
      */
    public micronaut.kafka.avro.model.JuristicPerson.Builder setType(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.type = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * type of person i.e. NaturalPerson or JuristicPerson
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'type' field.
      * type of person i.e. NaturalPerson or JuristicPerson
      * @return This builder.
      */
    public micronaut.kafka.avro.model.JuristicPerson.Builder clearType() {
      type = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'name' field.
      * @return The value.
      */
    public java.lang.CharSequence getName() {
      return name;
    }


    /**
      * Sets the value of the 'name' field.
      * @param value The value of 'name'.
      * @return This builder.
      */
    public micronaut.kafka.avro.model.JuristicPerson.Builder setName(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.name = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'name' field.
      * @return This builder.
      */
    public micronaut.kafka.avro.model.JuristicPerson.Builder clearName() {
      name = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JuristicPerson build() {
      try {
        JuristicPerson record = new JuristicPerson();
        record.type = fieldSetFlags()[0] ? this.type : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.name = fieldSetFlags()[1] ? this.name : (java.lang.CharSequence) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<JuristicPerson>
    WRITER$ = (org.apache.avro.io.DatumWriter<JuristicPerson>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<JuristicPerson>
    READER$ = (org.apache.avro.io.DatumReader<JuristicPerson>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.type);

    out.writeString(this.name);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.type = in.readString(this.type instanceof Utf8 ? (Utf8)this.type : null);

      this.name = in.readString(this.name instanceof Utf8 ? (Utf8)this.name : null);

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.type = in.readString(this.type instanceof Utf8 ? (Utf8)this.type : null);
          break;

        case 1:
          this.name = in.readString(this.name instanceof Utf8 ? (Utf8)this.name : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










