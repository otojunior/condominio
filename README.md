# conversor
Conversor de Unidades

[![Build Status](https://travis-ci.org/otojunior/conversor.svg?branch=master)](https://travis-ci.org/otojunior/conversor)
[![Coverage Status](https://coveralls.io/repos/github/otojunior/conversor/badge.svg?branch=master)](https://coveralls.io/github/otojunior/conversor?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d2ec5cafd0f5428a860b4f47328dd87f)](https://www.codacy.com/app/otojunior/conversor?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=otojunior/conversor&amp;utm_campaign=Badge_Grade)

3. Coveralls and Codacy Integration
-----------------------------------

```xml
<plugin>
	<groupId>org.eluder.coveralls</groupId>
	<artifactId>coveralls-maven-plugin</artifactId>
	<version>4.3.0</version>
</plugin>

<plugin>
	<groupId>com.gavinmogan</groupId>
    <artifactId>codacy-maven-plugin</artifactId>
    <version>1.0.3</version>
    <executions>
    	<execution>
    		<goals>
    			<goal>coverage</goal>
    		</goals>
    		<configuration>
		    	<coverageReportFile>target/site/jacoco/jacoco.xml</coverageReportFile>
		    	<projectToken>4f56fb080b83441494c31f1180164d25</projectToken>
		    	<apiToken>4f56fb080b83441494c31f1180164d25</apiToken>
    		</configuration>
    	</execution>
    </executions>
</plugin>

```
**projectToken** e **apiToken** got from Codacy project.

Add follow lines in **.travis.yml**:

```
install: true
script:  mvn clean test jacoco:report
after_success:
  - mvn coveralls:report
```