import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        line = line.replaceAll("[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~«»…„]", "");
        line = line.toLowerCase();

        String[] words = line.split(" ");
        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }
    }
}