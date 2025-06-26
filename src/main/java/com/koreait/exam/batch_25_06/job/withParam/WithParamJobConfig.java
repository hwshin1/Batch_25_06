package com.koreait.exam.batch_25_06.job.withParam;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WithParamJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job WithParamJob() {
        return jobBuilderFactory.get("WithParamJob")
//                .incrementer(new RunIdIncrementer())
                .start(WithParamStep1())
                .build();
    }

    @Bean
    @JobScope
    public Step WithParamStep1() {
        return stepBuilderFactory.get("WithParamStep1")
                .tasklet(WithParamTasklet1())
                .build();
    }

    @Bean
    @StepScope
    public Tasklet WithParamTasklet1() {
        return (StepContribution contribution, ChunkContext chunkContext) -> {
            System.out.println("withParam1!");
            return RepeatStatus.FINISHED;
        };
    }
}
