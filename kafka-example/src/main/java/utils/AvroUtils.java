package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;

import exception.AvroException;

public class AvroUtils {

	public <T extends SpecificRecordBase> void serialize(List<T> lists, String filePath, Class<T> obj)
			throws AvroException {

		if (0 == lists.size()) {
			return;
		}

		DatumWriter<T> userDatumWriter = new SpecificDatumWriter<T>(obj);
		T instance = lists.get(0);
		try (DataFileWriter<T> dataFileWriter = new DataFileWriter<T>(userDatumWriter)) {
			dataFileWriter.create(instance.getSchema(), new File(filePath));
			for (T element : lists) {
				dataFileWriter.append(element);
			}
		} catch (IOException e) {
			throw new AvroException("DataFileWriter Operate Exception!", e);
		}
	}

	public <T extends SpecificRecordBase> void deserialize(String filePath, Class<T> obj) throws AvroException {

		DatumReader<T> userDatumReader = new SpecificDatumReader<T>(obj);
		T ret = null;
		try (DataFileReader<T> dataFileReader = new DataFileReader<T>(new File(filePath), userDatumReader)) {
			while (dataFileReader.hasNext()) {
				ret = dataFileReader.next(ret);
				// TODO
				System.out.println(ret);
			}
		} catch (IOException e) {
			throw new AvroException("DataFileReader Operate Exception!", e);
		}
	}

	public void serializeWithSchema(List<GenericRecord> lists, String filePath, String schemaPath)
			throws AvroException {

		if (0 == lists.size()) {
			return;
		}

		Schema schema;
		DatumWriter<GenericRecord> userDatumWriter;
		DataFileWriter<GenericRecord> dataFileWriter = null;

		try {
			schema = new Schema.Parser().parse(new File(schemaPath));
			userDatumWriter = new SpecificDatumWriter<GenericRecord>(schema);
			dataFileWriter = new DataFileWriter<GenericRecord>(userDatumWriter);
			dataFileWriter.create(schema, new File(filePath));
			for (GenericRecord element : lists) {
				dataFileWriter.append(element);
			}
		} catch (IOException e) {
			throw new AvroException("DataFileWriter Operate Exception!", e);
		} finally {
			if (dataFileWriter != null) {
				try {
					dataFileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void deserializeWithSchema(String filePath, String schemaPath) throws AvroException {
		Schema schema;
		DatumReader<GenericRecord> userDatumReader;
		DataFileReader<GenericRecord> dataFileReader = null;
		GenericRecord ret = null;
		try {
			schema = new Schema.Parser().parse(new File(schemaPath));
			userDatumReader = new SpecificDatumReader<GenericRecord>(schema);
			dataFileReader = new DataFileReader<GenericRecord>(new File(filePath), userDatumReader);
			while (dataFileReader.hasNext()) {
				ret = dataFileReader.next(ret);
				// TODO
				System.out.println(ret);
			}
		} catch (IOException e) {
			throw new AvroException("DataFileReader Operate Exception!", e);
		} finally {
			if (null != dataFileReader) {
				try {
					dataFileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws AvroException, IOException {

		// List<User> users = new ArrayList<>();
		// users.add(User.newBuilder().setName("jean").setFavoriteNumber(1).setFavoriteColor("red").build());
		AvroUtils avroUtils = new AvroUtils();

		// avroUtils.serialize(users,
		// "C:\\Users\\Administrator\\Desktop\\data.avro", User.class);
		// avroUtils.deserialize("C:\\Users\\Administrator\\Desktop\\data.avro",
		// User.class);
		List<GenericRecord> users = new ArrayList<>();
		Schema schema = new Schema.Parser()
				.parse(new File("E:\\pself\\kafka-example\\src\\main\\java\\avro\\source\\user.avsc"));
		GenericRecord user1 = new GenericData.Record(schema);
		user1.put("name", "Alyssa");
		user1.put("favorite_number", 256);
		users.add(user1);
		// avroUtils.serializeWithSchema(users,
		// "C:\\Users\\Administrator\\Desktop\\data1.avro",
		// "E:\\pself\\kafka-example\\src\\main\\java\\avro\\source\\user.avsc");
		avroUtils.deserializeWithSchema("C:\\Users\\Administrator\\Desktop\\data1.avro",
				"E:\\pself\\kafka-example\\src\\main\\java\\avro\\source\\user.avsc");

	}

}
