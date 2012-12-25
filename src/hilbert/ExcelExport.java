package hilbert;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Delon Newman
 */
public class ExcelExport {
	private final static String[] FIELDS = {
		"Course Name",
		"Building",
		"Room",
		"Days",
		"Start Class Time",
		"End Class Time",
		"Capacity",
		"Faculty"
	};
	
	private final RaplaImport  data;
	private final HSSFWorkbook workbook;
	private final Sheet        sheet;
	
	private ExcelExport(RaplaImport data) {
		this.data     = data;
		this.workbook = new HSSFWorkbook();
		this.sheet    = this.workbook.createSheet();
	}
	
	public static ExcelExport create(RaplaImport data) {
		return new ExcelExport(data)
				.writeFields()
				.build();
	}
	
	private ExcelExport build() {
		for ( int i = 0; i < data.courses().size(); i++ ) {
			Row row   = this.sheet.createRow(i + 1);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(data.courses().get(i).getName());
		}
		
		return this;
	}
	
	private ExcelExport writeFields() {
		Row row = this.sheet.createRow(0);
		
		for ( int i = 0; i < FIELDS.length; i++ ) {
			Cell cell = row.createCell(i);
			cell.setCellValue(FIELDS[i]);
		}
		
		return this;
	}
	
	public void save(String path) throws IOException {
		FileOutputStream file = new FileOutputStream(path);
		this.workbook.write(file);
		file.close();
	}
}
