package com.wesdom.compa.backend.service.implementation;


import com.wesdom.compa.backend.database.enums.PaginationParamsEnum;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author randy
 */
public class PredicateBuilderServiceImpl<T> implements IPredicateBuilder<T> {

    /**
     * Method to save dynamic predicates for query using specification jpa
     * API.
     * @param params
     */
    @Override
    public Specification<T> createPredicate(Map<String, String> params) {
        boolean index = false;
        List<String> paginationParams = Arrays.asList(PaginationParamsEnum.values()).
                stream().map(x -> x.getTag()).collect(Collectors.toList());
        Specification<T> specifications = null;
        for (String k : params.keySet()) {
            //Omits pagination paramas
            if (!paginationParams.contains(k)) {
                String value = params.get(k);
                if (index) {
                    specifications = specifications.and(createSpecification(k, value));
                } else {
                    specifications = Specification.where(createSpecification(k, value));
                    index = true;
                }
            }
        }

        return specifications;
    }

    /**
     * Specification builder.
     */
    private Specification<T> createSpecification(String prop, String stringValue) {
        Pattern p = Pattern.compile("\\(([^\\)]+),([^\\)]+)\\)");
        Matcher m = p.matcher(stringValue);
        boolean isRange = m.find();
        String value = isRange ? m.group().substring( 1 , stringValue.length() -1 ) : stringValue;
        boolean isNot = prop.charAt(prop.length() - 1) == '!';
        String property = isNot ? prop.substring(0,prop.length() - 1) : prop;

        if(isRange){
            return (root, query, builder) -> {
                Expression<T> path = buildPath(root, property);
                List<Object> l = (List<Object>) processValue(path,value);
                if(path.getJavaType().equals(LocalDate.class)){
                    return builder.between(path.as((LocalDate.class)), (LocalDate)l.get(0),(LocalDate)l.get(1));
                }else if(path.getJavaType().equals(String.class)){
                    return builder.between(path.as((String.class)), l.get(0).toString(),l.get(1).toString());
                }
                return builder.between(path.as((Double.class)), Double.parseDouble(l.get(0).toString()),Double.parseDouble(l.get(1).toString()));
            };
        }

        return (root, query, builder) -> {
            Expression<T> path = buildPath(root, property);

            if(path.getJavaType().equals(LocalDate.class)){
                if(value.contains(",")){
                    return path.as(LocalDate.class).in(processValue(path,value));
                }
//                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//                LocalDate d = LocalDate.parse(value,dtf);
                return builder.equal(path.as((LocalDate.class)),processValue(path,value));
            }else if(path.getJavaType().equals(Boolean.class)){
                if(value.contains(",")){
                    return path.as(Boolean.class).in(processValue(path,value));
                }
                return builder.equal(path.as((Boolean.class)), processValue(path,value));
            }else if(!path.getJavaType().equals(String.class)){
                if(value.contains(",")){
                    if(isNot){
                        return builder.not(path.as(String.class).in(processValue(path,value)));
                    }
                    return path.as(String.class).in(processValue(path,value));
                }
                if(isNot){
                    return builder.not(builder.equal(path.as((String.class)), processValue(path,value)));
                }
                return builder.equal(path.as((String.class)), processValue(path,value));
            }
            if(value.contains(",")){
                if(isNot){
                    return builder.not(path.as(String.class).in(processValue(path,value)));
                }
                return path.as(String.class).in(processValue(path,value));
            }
            String val = "%"+processValue(path,value).toString()+"%";
            if(isNot){
                return builder.not(builder.like(path.as((String.class)), val));
            }
            return builder.like(path.as((String.class)), val);
        };
    }

    /**
     * Method to save Path to access nested properties in entity.
     */
    private Path<T> buildPath(Root<T> root, String property) {
        Path<T> p = root;
        for (String s : property.split("\\.")) {
            if(Collection.class.isAssignableFrom(p.get(s).getJavaType())){
                p = ((From) p).join(s);
            }else {
                p = p.get(s);
            }
        }
        return p;
    }

    private Object processValue(Expression<T> property,String value){
        if(property.getJavaType().equals(LocalDate.class)){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            if(value.contains(",")){
                return Arrays.asList(value.split(",")).stream().map(x -> {
                        return LocalDate.parse(x,dtf);
                }).collect(Collectors.toList());
            }else {
                return LocalDate.parse(value,dtf);
            }
        }else if(property.getJavaType().equals(Boolean.class)){
            if(value.contains(",")){
                return Arrays.asList(value.split(",")).stream().map(x -> Boolean.parseBoolean(x)).collect(Collectors.toList());
            }
            return Boolean.parseBoolean(value);
        }
        if(value.contains(",")){
            return Arrays.asList(value.split(","));
        }
        return value;
    }

}