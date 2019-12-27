package com.gootschool.search.listener;

import com.gootschool.common.constants.MQRoutingType;
import com.gootschool.search.service.ISearchGoodsService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseListener {

    @Autowired
    private ISearchGoodsService searchGoodsService;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(
                            value = "revan.create.index.queue", durable = "true"//队列持久化
                    ),
                    exchange = @Exchange(
                            value = MQRoutingType.COURSE_EXCHANGE,
                            ignoreDeclarationExceptions = "true",
                            type = ExchangeTypes.TOPIC
                    ),
                    key = {MQRoutingType.COURSE_PUBLISH_ROUTE}
            )
    )
    public void coursePublish(String id) {
        // 保存到索引库
        this.searchGoodsService.publishGoodsByCourseId(id);
    }
}
