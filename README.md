# benchmark
Simple java project to compare algorithm performance

## Compiling with Maven 

``` mvn compile ```

``` 
mvn package dependency:copy-dependencies 
```

## Compiling with Gradle 

```
gradle distZip
```

---

## Sample result

* GetsetExample = this is a pure java exemple accessing class fields
* ReflectionExample = the same funcionality using pure java reflection
* BeanUtilsExample  = the same example using the library Apache BenUtils
---

###  GetsetExample
| Min      | AVG       | Max       | Variation |
|----------|-----------|-----------|-----------|
|      0ns |      3ms  |     63ms  |      3ms  |

### ReflectionExample
| Min      | AVG       | Max       | Variation |
|----------|-----------|-----------|-----------|
|      7ms |     21ms  |    311ms  |     19ms  |

### BeanUtilsExample
| Min      | AVG       | Max       | Variation |
|----------|-----------|-----------|-----------|
|      4ms |     50ms  |      2se  |     72ms  |
