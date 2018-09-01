package stream;

import java.io.File;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;

import stream.model.avro.UserAvro;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		UserAvro user1 = new UserAvro();
		user1.setName("user1");
		user1.setTimeStamp(123456789l);

		UserAvro user2 = new UserAvro("user2", 123456790l);

		UserAvro user3 = UserAvro.newBuilder().setName("user3").setTimeStamp(123456791l).build();

		// Serialize user1, user2 and user3 to disk
		// DatumWriter<User> userDatumWriter = new
		// SpecificDatumWriter<User>(User.class);
		//
		// DataFileWriter<User> dataFileWriter = new
		// DataFileWriter<User>(userDatumWriter);
		//
		// dataFileWriter.create(user1.getSchema(), new File("users.avro"));
		//
		// dataFileWriter.append(user1);
		// dataFileWriter.append(user2);
		// dataFileWriter.append(user3);
		// dataFileWriter.close();

		// Deserialize Users from disk
		DatumReader<UserAvro> userDatumReader = new SpecificDatumReader<UserAvro>(UserAvro.class);
		DataFileReader<UserAvro> dataFileReader = new DataFileReader<UserAvro>(new File("users.avro"), userDatumReader);
		UserAvro user = null;
		while (dataFileReader.hasNext()) {
			// Reuse user object by passing it to next(). This saves us from
			// allocating and garbage collecting many objects for files with
			// many items.
			user = dataFileReader.next(user);
			System.out.println(user);
		}

	}

}
