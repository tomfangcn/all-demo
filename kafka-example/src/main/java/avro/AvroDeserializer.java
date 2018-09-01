package avro;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.serialization.Deserializer;

public class AvroDeserializer<T extends SpecificRecord> implements Deserializer<T> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public T deserialize(String topic, byte[] data) {
		BinaryDecoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(data), null);
		// TODO 待优化
		DatumReader<T> datumReader = new SpecificDatumReader<>(new User().getSchema());
		try {
			return datumReader.read(null, binaryDecoder);
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
