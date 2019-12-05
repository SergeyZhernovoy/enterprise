package com.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@EnableBatchProcessing
@SpringBootApplication
public class BatchApplication {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    @StepScope
    public Tasklet helloWorldTasklet(@Value("#{jobParameters['name']}") String name,
                                     @Value("#{jobParameters['fileName']}") String fileName) {
        return (contribution, chunkContext) -> {
            System.out.println(String.format("Hello , %s!", name));
            System.out.println(String.format("fileName = %s!", fileName));
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step step() {

        return this.stepBuilderFactory.get("step")
                .tasklet(helloWorldTasklet(null, null))
                .build();
    }

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("job")
                .start(step())
                .validator(validator())
                .incrementer(new DailyJobTimestamper())
                .listener(JobListenerFactoryBean.getListener(new JobLoggerListener()))
                .next(step1())
//                .next(step2())
//                .next(step3())
                .build();
    }

    @Bean
    public CompositeJobParametersValidator validator() {
        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();
        DefaultJobParametersValidator defaultJobParametersValidator = new DefaultJobParametersValidator(
                new String[]{"fileName"},
                new String[]{"name", "currentDate"}
        );
        defaultJobParametersValidator.afterPropertiesSet();
        validator.setValidators(Arrays.asList(new ParameterValidator(), defaultJobParametersValidator));
        return validator;
    }

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1")
                .tasklet(new HelloWorldTasklet())
                .listener(promotionListener())
                .build();
    }

    @Bean
    public Step step2() {
        return this.stepBuilderFactory.get("step2")
                .tasklet(new GoodByeTasklet())
                .build();
    }

    @Bean
    public StepExecutionListener promotionListener() {
        ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
        listener.setKeys(new String[]{"name"});
        return listener;
    }

    @Bean
    public Step step3() {
        return this.stepBuilderFactory.get("step3")
                .<String, String>chunk(1000)
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }

    @Bean
    @StepScope
    public ListItemReader<String> itemReader() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 1500; i++) {
            items.add(UUID.randomUUID().toString());
        }
        return new ListItemReader<>(items);
    }

    @Bean
    @StepScope
    public ItemWriter<String> itemWriter() {
        return items -> {
            int index = 1;
            for (String item : items) {
                System.out.println(String.format(">> current %s item = %s", String.valueOf(index++), item));
            }
        };
    }

    /*
    @Bean
    @StepScope
    public FlatFileItemReader<String> itemReader(@Value("#{jobParameters['inputFile']}") Resource inputFile) {
        return new FlatFileItemReaderBuilder<String>()
                .name("itemReader")
                .resource(inputFile)
                .lineMapper(new PassThroughLineMapper())
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<String> itemWriter(@Value("#{jobParameters['outputFile']}") Resource outputFile) {
        return new FlatFileItemWriterBuilder<String>()
                .name("itemWriter")
                .resource(outputFile)
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }
*/
    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

}
