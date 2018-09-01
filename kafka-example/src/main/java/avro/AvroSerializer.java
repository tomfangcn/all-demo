package avro;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.serialization.Serializer;

public class AvroSerializer<T extends SpecificRecord> implements Serializer<T> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] serialize(String topic, T data) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(outputStream, null);
		DatumWriter<T> writer = new SpecificDatumWriter<>(data.getSchema());

		try {
			writer.write(data, binaryEncoder);
			return outputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
