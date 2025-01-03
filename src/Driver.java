import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

public class Driver {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Customer Segmentation");

        job.setJarByClass(Driver.class);
        job.setMapperClass(CustomerMapper.class);
        job.setReducerClass(CustomerReducer.class);

        // Set output types
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // Set input and output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));  // Input Path
        FileOutputFormat.setOutputPath(job, new Path(args[1])); // Output Path

        // Wait for job completion
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
