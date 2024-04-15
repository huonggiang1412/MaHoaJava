import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FolderCreation {
	public static void main(String[] args) {
		String rootDirectory = "D:\\";

		String securityFolderName = "Security";
		String encryptionFolderName = "Encryption";

		String fileName = "file.txt";

		File securityFolder = new File(rootDirectory, securityFolderName);
		if (securityFolder.exists()) {
		    System.out.println("Thư mục " + securityFolder.getAbsolutePath() + " đã tồn tại.");
		} else{
			if (securityFolder.mkdir()) {
				System.out.println("Đã tạo thư mục " + securityFolder.getAbsolutePath());
			} else {
				System.out.println("Không thể tạo thư mục " + securityFolder.getAbsolutePath());
				return;
			}
		}

		File encryptionFolder = new File(securityFolder, encryptionFolderName);
		if (encryptionFolder.exists()) {
		    System.out.println("Thư mục " + encryptionFolder.getAbsolutePath() + " đã tồn tại.");
		} else {
			if (encryptionFolder.mkdir()) {
				System.out.println("Đã tạo thư mục " + encryptionFolder.getAbsolutePath());
			} else {
				System.out.println("Không thể tạo thư mục " + encryptionFolder.getAbsolutePath());
				return;
			}
		}

		File file = new File(encryptionFolder, fileName);
		try {
			if (file.createNewFile()) {
				System.out.println("Đã tạo tệp tin " + file.getAbsolutePath());
			} else {
				System.out.println("Không thể tạo tệp tin " + file.getAbsolutePath());
				return;
			}
		} catch (IOException e) {
			System.out.println("Lỗi khi tạo tệp tin " + file.getAbsolutePath() + ": " + e.getMessage());
			return;
		}

		try {
			byte[] fileContent = Files.readAllBytes(file.toPath());

			MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");

			byte[] sha256 = sha256Digest.digest(fileContent);

			String sha256Hex = bytesToHex(sha256);

			System.out.println("Giá trị băm SHA-256 của tệp tin: " + sha256Hex);
		} catch (NoSuchAlgorithmException | IOException e) {
			System.out.println("Lỗi khi tính toán giá trị băm SHA-256: " + e.getMessage());
		}
	}

	private static String bytesToHex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte b : bytes) {
			result.append(String.format("%02x", b));
		}
		return result.toString();
	}
}