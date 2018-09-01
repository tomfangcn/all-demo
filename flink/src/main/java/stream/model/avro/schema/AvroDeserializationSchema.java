package stream.model.avro.schema;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import stream.model.avro.UserAvro;

public class AvroDeserializationSchema<T> implements DeserializationSchema<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TypeInformation<T> getProducedType() {
		// TODO Auto-generated method stub
		return TypeInformation.of(new TypeHint<T>() {
		});
	}

	public T deserialize(byte[] message) throws IOException {
		// TODO Auto-generated method stub

		BinaryDecoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(message), null);

		DatumReader<T> userDatumReader = new SpecificDatumReader<T>(new UserAvro().getSchema());

		return (T) userDatumReader.read(null, binaryDecoder);

	}

	public boolean isEndOfStream(T nextElement) {
		// TODO Auto-generated method stub
		return false;
	}

}
