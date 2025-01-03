import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

import java.io.IOException;

public class CustomerReducer extends Reducer<Text, Text, Text, Text> {

    private Text outputValue = new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // Write header line to the output
        context.write(new Text("CustomerID"), new Text("TotalSpend\tFrequency\tRecency"));
    }

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        double totalSpend = 0.0;
        int frequency = 0;
        long lastInvoiceDate = 0;

        // Process all values for the customer (assuming TotalSpend, Frequency, Recency calculation here)
        for (Text val : values) {
            String[] valueArray = val.toString().split(",");
            totalSpend += Double.parseDouble(valueArray[0]); // Add the TotalSpend (assumed to be first)
            frequency += 1;  // Increment frequency
            long invoiceDate = Long.parseLong(valueArray[2]); // Parse the timestamp of the invoice
            if (invoiceDate > lastInvoiceDate) {
                lastInvoiceDate = invoiceDate;  // Get the most recent invoice date
            }
        }

        // Calculate Recency (in days, assuming last invoice date is in timestamp)
        long recency = (System.currentTimeMillis() - lastInvoiceDate) / (1000 * 60 * 60 * 24);  // Convert to days

        // Prepare output value in CSV format
        String result = String.format("%.1f\t%d\t%d", totalSpend, frequency, recency);
        outputValue.set(result);
        context.write(key, outputValue);
    }
}
