// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: dbdata.proto

package com.zzx.protoClasses;

public final class DbDataProto {
  private DbDataProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface DbDataOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.zzx.protoClasses.DbData)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 msg_id = 1;</code>
     */
    boolean hasMsgId();
    /**
     * <code>required int32 msg_id = 1;</code>
     */
    int getMsgId();
  }
  /**
   * Protobuf type {@code com.zzx.protoClasses.DbData}
   */
  public  static final class DbData extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.zzx.protoClasses.DbData)
      DbDataOrBuilder {
    // Use DbData.newBuilder() to construct.
    private DbData(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private DbData() {
      msgId_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private DbData(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              msgId_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.zzx.protoClasses.DbDataProto.internal_static_com_zzx_protoClasses_DbData_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.zzx.protoClasses.DbDataProto.internal_static_com_zzx_protoClasses_DbData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.zzx.protoClasses.DbDataProto.DbData.class, com.zzx.protoClasses.DbDataProto.DbData.Builder.class);
    }

    private int bitField0_;
    public static final int MSG_ID_FIELD_NUMBER = 1;
    private int msgId_;
    /**
     * <code>required int32 msg_id = 1;</code>
     */
    public boolean hasMsgId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 msg_id = 1;</code>
     */
    public int getMsgId() {
      return msgId_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasMsgId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, msgId_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, msgId_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.zzx.protoClasses.DbDataProto.DbData)) {
        return super.equals(obj);
      }
      com.zzx.protoClasses.DbDataProto.DbData other = (com.zzx.protoClasses.DbDataProto.DbData) obj;

      boolean result = true;
      result = result && (hasMsgId() == other.hasMsgId());
      if (hasMsgId()) {
        result = result && (getMsgId()
            == other.getMsgId());
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasMsgId()) {
        hash = (37 * hash) + MSG_ID_FIELD_NUMBER;
        hash = (53 * hash) + getMsgId();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.zzx.protoClasses.DbDataProto.DbData parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.zzx.protoClasses.DbDataProto.DbData parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.zzx.protoClasses.DbDataProto.DbData parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.zzx.protoClasses.DbDataProto.DbData parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.zzx.protoClasses.DbDataProto.DbData parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.zzx.protoClasses.DbDataProto.DbData parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.zzx.protoClasses.DbDataProto.DbData parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.zzx.protoClasses.DbDataProto.DbData parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.zzx.protoClasses.DbDataProto.DbData parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.zzx.protoClasses.DbDataProto.DbData parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.zzx.protoClasses.DbDataProto.DbData parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.zzx.protoClasses.DbDataProto.DbData parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.zzx.protoClasses.DbDataProto.DbData prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.zzx.protoClasses.DbData}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.zzx.protoClasses.DbData)
        com.zzx.protoClasses.DbDataProto.DbDataOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.zzx.protoClasses.DbDataProto.internal_static_com_zzx_protoClasses_DbData_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.zzx.protoClasses.DbDataProto.internal_static_com_zzx_protoClasses_DbData_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.zzx.protoClasses.DbDataProto.DbData.class, com.zzx.protoClasses.DbDataProto.DbData.Builder.class);
      }

      // Construct using com.zzx.protoClasses.DbDataProto.DbData.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        msgId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.zzx.protoClasses.DbDataProto.internal_static_com_zzx_protoClasses_DbData_descriptor;
      }

      public com.zzx.protoClasses.DbDataProto.DbData getDefaultInstanceForType() {
        return com.zzx.protoClasses.DbDataProto.DbData.getDefaultInstance();
      }

      public com.zzx.protoClasses.DbDataProto.DbData build() {
        com.zzx.protoClasses.DbDataProto.DbData result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.zzx.protoClasses.DbDataProto.DbData buildPartial() {
        com.zzx.protoClasses.DbDataProto.DbData result = new com.zzx.protoClasses.DbDataProto.DbData(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.msgId_ = msgId_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.zzx.protoClasses.DbDataProto.DbData) {
          return mergeFrom((com.zzx.protoClasses.DbDataProto.DbData)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.zzx.protoClasses.DbDataProto.DbData other) {
        if (other == com.zzx.protoClasses.DbDataProto.DbData.getDefaultInstance()) return this;
        if (other.hasMsgId()) {
          setMsgId(other.getMsgId());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (!hasMsgId()) {
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.zzx.protoClasses.DbDataProto.DbData parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.zzx.protoClasses.DbDataProto.DbData) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int msgId_ ;
      /**
       * <code>required int32 msg_id = 1;</code>
       */
      public boolean hasMsgId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 msg_id = 1;</code>
       */
      public int getMsgId() {
        return msgId_;
      }
      /**
       * <code>required int32 msg_id = 1;</code>
       */
      public Builder setMsgId(int value) {
        bitField0_ |= 0x00000001;
        msgId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 msg_id = 1;</code>
       */
      public Builder clearMsgId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        msgId_ = 0;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.zzx.protoClasses.DbData)
    }

    // @@protoc_insertion_point(class_scope:com.zzx.protoClasses.DbData)
    private static final com.zzx.protoClasses.DbDataProto.DbData DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.zzx.protoClasses.DbDataProto.DbData();
    }

    public static com.zzx.protoClasses.DbDataProto.DbData getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<DbData>
        PARSER = new com.google.protobuf.AbstractParser<DbData>() {
      public DbData parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new DbData(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<DbData> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<DbData> getParserForType() {
      return PARSER;
    }

    public com.zzx.protoClasses.DbDataProto.DbData getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_zzx_protoClasses_DbData_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_zzx_protoClasses_DbData_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014dbdata.proto\022\024com.zzx.protoClasses\"\030\n\006" +
      "DbData\022\016\n\006msg_id\030\001 \002(\005B#\n\024com.zzx.protoC" +
      "lassesB\013DbDataProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_zzx_protoClasses_DbData_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_zzx_protoClasses_DbData_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_zzx_protoClasses_DbData_descriptor,
        new java.lang.String[] { "MsgId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
