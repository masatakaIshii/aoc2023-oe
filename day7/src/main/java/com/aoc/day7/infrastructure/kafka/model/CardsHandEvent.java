/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.aoc.day7.infrastructure.kafka.model;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class CardsHandEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 766495251582490307L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"CardsHandEvent\",\"namespace\":\"com.aoc.day7.infrastructure.kafka.model\",\"fields\":[{\"name\":\"order\",\"type\":\"long\"},{\"name\":\"cards\",\"type\":\"string\"},{\"name\":\"bid\",\"type\":\"long\"},{\"name\":\"score\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<CardsHandEvent> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<CardsHandEvent> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<CardsHandEvent> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<CardsHandEvent> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<CardsHandEvent> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this CardsHandEvent to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a CardsHandEvent from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a CardsHandEvent instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static CardsHandEvent fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private long order;
  private java.lang.CharSequence cards;
  private long bid;
  private long score;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public CardsHandEvent() {}

  /**
   * All-args constructor.
   * @param order The new value for order
   * @param cards The new value for cards
   * @param bid The new value for bid
   * @param score The new value for score
   */
  public CardsHandEvent(java.lang.Long order, java.lang.CharSequence cards, java.lang.Long bid, java.lang.Long score) {
    this.order = order;
    this.cards = cards;
    this.bid = bid;
    this.score = score;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return order;
    case 1: return cards;
    case 2: return bid;
    case 3: return score;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: order = (java.lang.Long)value$; break;
    case 1: cards = (java.lang.CharSequence)value$; break;
    case 2: bid = (java.lang.Long)value$; break;
    case 3: score = (java.lang.Long)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'order' field.
   * @return The value of the 'order' field.
   */
  public long getOrder() {
    return order;
  }


  /**
   * Sets the value of the 'order' field.
   * @param value the value to set.
   */
  public void setOrder(long value) {
    this.order = value;
  }

  /**
   * Gets the value of the 'cards' field.
   * @return The value of the 'cards' field.
   */
  public java.lang.CharSequence getCards() {
    return cards;
  }


  /**
   * Sets the value of the 'cards' field.
   * @param value the value to set.
   */
  public void setCards(java.lang.CharSequence value) {
    this.cards = value;
  }

  /**
   * Gets the value of the 'bid' field.
   * @return The value of the 'bid' field.
   */
  public long getBid() {
    return bid;
  }


  /**
   * Sets the value of the 'bid' field.
   * @param value the value to set.
   */
  public void setBid(long value) {
    this.bid = value;
  }

  /**
   * Gets the value of the 'score' field.
   * @return The value of the 'score' field.
   */
  public long getScore() {
    return score;
  }


  /**
   * Sets the value of the 'score' field.
   * @param value the value to set.
   */
  public void setScore(long value) {
    this.score = value;
  }

  /**
   * Creates a new CardsHandEvent RecordBuilder.
   * @return A new CardsHandEvent RecordBuilder
   */
  public static com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder newBuilder() {
    return new com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder();
  }

  /**
   * Creates a new CardsHandEvent RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new CardsHandEvent RecordBuilder
   */
  public static com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder newBuilder(com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder other) {
    if (other == null) {
      return new com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder();
    } else {
      return new com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder(other);
    }
  }

  /**
   * Creates a new CardsHandEvent RecordBuilder by copying an existing CardsHandEvent instance.
   * @param other The existing instance to copy.
   * @return A new CardsHandEvent RecordBuilder
   */
  public static com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder newBuilder(com.aoc.day7.infrastructure.kafka.model.CardsHandEvent other) {
    if (other == null) {
      return new com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder();
    } else {
      return new com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder(other);
    }
  }

  /**
   * RecordBuilder for CardsHandEvent instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<CardsHandEvent>
    implements org.apache.avro.data.RecordBuilder<CardsHandEvent> {

    private long order;
    private java.lang.CharSequence cards;
    private long bid;
    private long score;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.order)) {
        this.order = data().deepCopy(fields()[0].schema(), other.order);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.cards)) {
        this.cards = data().deepCopy(fields()[1].schema(), other.cards);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.bid)) {
        this.bid = data().deepCopy(fields()[2].schema(), other.bid);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.score)) {
        this.score = data().deepCopy(fields()[3].schema(), other.score);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing CardsHandEvent instance
     * @param other The existing instance to copy.
     */
    private Builder(com.aoc.day7.infrastructure.kafka.model.CardsHandEvent other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.order)) {
        this.order = data().deepCopy(fields()[0].schema(), other.order);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.cards)) {
        this.cards = data().deepCopy(fields()[1].schema(), other.cards);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.bid)) {
        this.bid = data().deepCopy(fields()[2].schema(), other.bid);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.score)) {
        this.score = data().deepCopy(fields()[3].schema(), other.score);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'order' field.
      * @return The value.
      */
    public long getOrder() {
      return order;
    }


    /**
      * Sets the value of the 'order' field.
      * @param value The value of 'order'.
      * @return This builder.
      */
    public com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder setOrder(long value) {
      validate(fields()[0], value);
      this.order = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'order' field has been set.
      * @return True if the 'order' field has been set, false otherwise.
      */
    public boolean hasOrder() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'order' field.
      * @return This builder.
      */
    public com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder clearOrder() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'cards' field.
      * @return The value.
      */
    public java.lang.CharSequence getCards() {
      return cards;
    }


    /**
      * Sets the value of the 'cards' field.
      * @param value The value of 'cards'.
      * @return This builder.
      */
    public com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder setCards(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.cards = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'cards' field has been set.
      * @return True if the 'cards' field has been set, false otherwise.
      */
    public boolean hasCards() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'cards' field.
      * @return This builder.
      */
    public com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder clearCards() {
      cards = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'bid' field.
      * @return The value.
      */
    public long getBid() {
      return bid;
    }


    /**
      * Sets the value of the 'bid' field.
      * @param value The value of 'bid'.
      * @return This builder.
      */
    public com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder setBid(long value) {
      validate(fields()[2], value);
      this.bid = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'bid' field has been set.
      * @return True if the 'bid' field has been set, false otherwise.
      */
    public boolean hasBid() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'bid' field.
      * @return This builder.
      */
    public com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder clearBid() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'score' field.
      * @return The value.
      */
    public long getScore() {
      return score;
    }


    /**
      * Sets the value of the 'score' field.
      * @param value The value of 'score'.
      * @return This builder.
      */
    public com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder setScore(long value) {
      validate(fields()[3], value);
      this.score = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'score' field has been set.
      * @return True if the 'score' field has been set, false otherwise.
      */
    public boolean hasScore() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'score' field.
      * @return This builder.
      */
    public com.aoc.day7.infrastructure.kafka.model.CardsHandEvent.Builder clearScore() {
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CardsHandEvent build() {
      try {
        CardsHandEvent record = new CardsHandEvent();
        record.order = fieldSetFlags()[0] ? this.order : (java.lang.Long) defaultValue(fields()[0]);
        record.cards = fieldSetFlags()[1] ? this.cards : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.bid = fieldSetFlags()[2] ? this.bid : (java.lang.Long) defaultValue(fields()[2]);
        record.score = fieldSetFlags()[3] ? this.score : (java.lang.Long) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<CardsHandEvent>
    WRITER$ = (org.apache.avro.io.DatumWriter<CardsHandEvent>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<CardsHandEvent>
    READER$ = (org.apache.avro.io.DatumReader<CardsHandEvent>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeLong(this.order);

    out.writeString(this.cards);

    out.writeLong(this.bid);

    out.writeLong(this.score);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.order = in.readLong();

      this.cards = in.readString(this.cards instanceof Utf8 ? (Utf8)this.cards : null);

      this.bid = in.readLong();

      this.score = in.readLong();

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.order = in.readLong();
          break;

        case 1:
          this.cards = in.readString(this.cards instanceof Utf8 ? (Utf8)this.cards : null);
          break;

        case 2:
          this.bid = in.readLong();
          break;

        case 3:
          this.score = in.readLong();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










