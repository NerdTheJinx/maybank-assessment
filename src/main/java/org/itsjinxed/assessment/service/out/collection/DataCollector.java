package org.itsjinxed.assessment.service.out.collection;

import org.itsjinxed.assessment.service.out.model.DataFragment;
import reactor.core.publisher.Flux;

public interface DataCollector<T extends DataFragment> {
    Flux<T> collect();
}
