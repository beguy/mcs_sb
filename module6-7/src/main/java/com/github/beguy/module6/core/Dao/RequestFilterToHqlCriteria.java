package com.github.beguy.module6.core.Dao;

import javax.persistence.Query;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

// Learning crunch
public class RequestFilterToHqlCriteria implements Cloneable {
    private String requestParameterName;
    private String requestParameter;
    private String jpqlCriteriaString;
    private boolean isJpqlCriteriaStringGenerated;

    private BiConsumer<RequestFilterToHqlCriteria, Query> queryParameterSetter = (thiz, query) -> {
        query.setParameter(thiz.getRequestParameterName(), thiz.getRequestParameter());
    };

    private BiFunction<RequestFilterToHqlCriteria, String, String> jpqlCriteriaStringGenerator = (thiz, requestParameter) -> {
        return thiz.jpqlCriteriaString;
    };

    public RequestFilterToHqlCriteria(String requestParameterName, String jpqlCriteriaDefaultString) {
        this.requestParameterName = requestParameterName;
        this.jpqlCriteriaString = jpqlCriteriaDefaultString;
        isJpqlCriteriaStringGenerated = true;
    }

    public String getRequestParameterName() {
        return requestParameterName;
    }

    public String getRequestParameter() {
        return requestParameter;
    }

    public void setRequestParameter(String requestParameter) {
        this.requestParameter = requestParameter;
    }

    public BiConsumer<RequestFilterToHqlCriteria, Query> getQueryParameterSetter() {
        return queryParameterSetter;
    }

    public void setQueryParameter(Query query){
        queryParameterSetter.accept(this, query);
    }

    public void setQueryParameterSetter(BiConsumer<RequestFilterToHqlCriteria, Query> queryParameterSetter) {
        this.queryParameterSetter = queryParameterSetter;
    }

    public String getGeneratedJpqlCriteriaString() {
        // lazy cache
        if (isJpqlCriteriaStringGenerated){
            return jpqlCriteriaString;
        } else {
            isJpqlCriteriaStringGenerated = true;
            jpqlCriteriaString = jpqlCriteriaStringGenerator.apply(this, requestParameter);
            return jpqlCriteriaString;
        }
    }

    public BiFunction<RequestFilterToHqlCriteria, String, String> getJpqlCriteriaStringGenerator() {
        return jpqlCriteriaStringGenerator;
    }

    public void setJpqlCriteriaStringGenerator(BiFunction<RequestFilterToHqlCriteria, String, String> jpqlCriteriaStringGenerator) {
        isJpqlCriteriaStringGenerated = false;
        this.jpqlCriteriaStringGenerator = jpqlCriteriaStringGenerator;
    }

    @Override
    public Object clone() {
        RequestFilterToHqlCriteria tmp = new RequestFilterToHqlCriteria(requestParameterName, jpqlCriteriaString);
        tmp.setRequestParameter(requestParameter);
        tmp.setQueryParameterSetter(queryParameterSetter);
        tmp.setJpqlCriteriaStringGenerator(jpqlCriteriaStringGenerator);
        return tmp;
    }
}
