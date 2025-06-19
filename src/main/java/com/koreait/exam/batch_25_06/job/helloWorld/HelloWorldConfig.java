package com.koreait.exam.batch_25_06.job.helloWorld;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@Configurable
@RequiredArgsConstructor
public class HelloWorldConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job helloWorldJob() {
        return new JobBuilder("helloWorldJob", jobRepository)
                .start(helloWorldStep1())
                .build();
    }

    @Bean
    public Step helloWorldStep1() {
        return new StepBuilder("helloWorldStep1", jobRepository)
                .tasklet(helloWorldTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet helloWorldTasklet() {
        return (StepContribution contribution, ChunkContext chunkContext) -> {
            System.out.println("helloWorld!!");
            return RepeatStatus.FINISHED;
        };
    }
}
