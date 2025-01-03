import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerMapper extends Mapper<LongWritable, Text, Text, Text> {
    private Text customerId = new Text();
    private Text outputValue = new Text();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // Skip the header line
        String line = value.toString();
        if (line.contains("InvoiceNo")) {
            return;
        }

        // Split the line into columns based on commas
        String[] columns = line.split(",");

        // Extract necessary fields
        String customer = columns[6];        // CustomerID
        String invoiceNo = columns[0];       // InvoiceNo
        String invoiceDate = columns[4];     // InvoiceDate
        double totalSales = 0.0;

        try {
            // Only parse TotalSales if it's a valid number (columns[8])
            totalSales = Double.parseDouble(columns[8]);  // TotalSales
        } catch (NumberFormatException e) {
            // If parsing fails, it means the field might be missing or non-numeric
            totalSales = 0.0; // Default to 0 if it's not a valid number
        }

        // Emit customer and corresponding data (InvoiceNo and TotalSales)
        try {
            Date date = dateFormat.parse(invoiceDate);
            long timestamp = date.getTime();
            String valueStr = totalSales + "," + invoiceNo + "," + timestamp;
            customerId.set(customer);
            outputValue.set(valueStr);
            context.write(customerId, outputValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
