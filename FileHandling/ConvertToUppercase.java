import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class ConvertToUppercase extends FileWriter {
	
	
	public void write(String str) throws IOException{
		String capitalString = "";
		for(int i=0; i<str.length();i++) {
			char c= str.charAt(i);
			int n=(int) c;
			if(97<=n && n<=122) {
				n=n-32;
			}
			capitalString = capitalString + (char) n;
		}
		write(capitalString, 0, str.length());
	}
	
	public void read() throws IOException {
		final FileReader r = new FileReader("file.txt");
		
		while (fileReader.hasNextLine()) {
		String data = myReader.nextLine();
		System.out.println(data);
		}
		r.close();
		
	}
	
	public static void main(String[] args) {
	
			ConvertToUppercase fileWritter = new ConvertToUppercase("file.txt");
			fileWritter.write("apple");
			fileWritter.close();
			fileWritter.read();
		}
		
		
		

}
