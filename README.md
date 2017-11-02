# DBpedia Spotlight [![Build Status](https://travis-ci.org/dbpedia-spotlight/dbpedia-spotlight-model.svg?branch=master)](https://travis-ci.org/dbpedia-spotlight/dbpedia-spotlight-model)


### Links

website - http://www.dbpedia-spotlight.org

status service	- http://status.dbpedia-spotlight.org

download service - http://download.dbpedia-spotlight.org

demo service - http://demo.dbpedia-spotlight.org

CI -http://jenkins.dbpedia-spotlight.org


### General Notes

Since v1.0, DBpedia Spotlight was split into two versions, under the same API,  as follow:

  - DBpedia-Spotlight-Model: described in [Improving Efficiency and Accuracy in Multilingual Entity Extraction](http://jodaiber.de/doc/entity.pdf)

  - DBpedia-Spotlight-Lucene: described in [DBpedia Spotlight: Shedding Light on the Web of Documents](http://www.dbpedia-spotlight.org/docs/spotlight.pdf)

This important movement was the way that we found to deliver faster fixes and new releases, providing solutions for each annotation approach.

Our first achievement is related with licensing. DBpedia Spotlight Model is now full compliance with Apache 2.0. It means that you can use it without any commercial restrictions.

We are so excited because there's even more great news to come.

If you require any further information, feel free to contact us via dbpedia@infai.org. We are already very excited to spend time with you on further community meetings and to publish new DBpedia releases.

Keep annotating,

All the best


#### Shedding Light on the Web of Documents

DBpedia Spotlight looks for ~3.5M things of unknown or ~320 known types in text and tries to link them to their global unique identifiers in [DBpedia](http://dbpedia.org).

#### Demonstration

Go to our [Demonstration](http://dbpedia-spotlight.github.io/demo/) page, copy+paste some text and play with the parameters to see how it works.


### Endpoints

http://model.dbpedia-spotlight.org/{LANGUAGE}/annotate

  - English: http://model.dbpedia-spotlight.org/en/annotate
  - German: http://model.dbpedia-spotlight.org/de/annotate
  - Dutch: http://model.dbpedia-spotlight.org/nl/annotate
  - French: http://model.dbpedia-spotlight.org/fr/annotate
  - Italian: http://model.dbpedia-spotlight.org/it/annotate
  - Russian: http://model.dbpedia-spotlight.org/ru/annotate
  - Spanish: http://model.dbpedia-spotlight.org/es/annotate
  - Portuguese: http://model.dbpedia-spotlight.org/pt/annotate
  - Hungarian: http://model.dbpedia-spotlight.org/hu/annotate
  - Turkish:  http://model.dbpedia-spotlight.org/tr/annotate


#### Call our web service

You can use our demonstration [Web Service](http://github.com/dbpedia-spotlight/dbpedia-spotlight/wiki/Web-service) directly from your application.

    curl http://model.dbpedia-spotlight.org/en/annotate  \
      --data-urlencode "text=President Obama called Wednesday on Congress to extend a tax break
      for students included in last year's economic stimulus package, arguing
      that the policy provides more generous assistance." \
      --data "confidence=0.35"

or for JSON:

    curl http://model.dbpedia-spotlight.org/en/annotate  \
      --data-urlencode "text=President Obama called Wednesday on Congress to extend a tax break
      for students included in last year's economic stimulus package, arguing
      that the policy provides more generous assistance." \
      --data "confidence=0.35" \
      -H "Accept: application/json"

#### Run your own server

If you need service reliability and lower response times, you can run DBpedia Spotlight in your own [In-House Server](https://github.com/dbpedia-spotlight/dbpedia-spotlight/wiki/Installation). Just download a model and Spotlight from [here](http://model.dbpedia-spotlight.org) to get started.

    wget http://downloads.dbpedia-spotlight.org/spotlight/dbpedia-spotlight-1.0.0.jar
    wget http://downloads.dbpedia-spotlight.org/2016-04/en/model/en.tar.gz
    tar xzf en.tar.gz
    java -jar dbpedia-spotlight-1.0.jar en http://localhost:2222/rest

#### Models and data

Models and raw data for most languages are available [here](http://downloads.dbpedia-spotlight.org).

## Citation

If you use DBpedia Spotlight in your research, please cite the following paper:

```bibtex
@inproceedings{isem2013daiber,
  title = {Improving Efficiency and Accuracy in Multilingual Entity Extraction},
  author = {Joachim Daiber and Max Jakob and Chris Hokamp and Pablo N. Mendes},
  year = {2013},
  booktitle = {Proceedings of the 9th International Conference on Semantic Systems (I-Semantics)}
}
```


## Licenses

All the original code produced for DBpedia Spotlight Model is licensed under  [Apache License, 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).

## Documentation

More documentation is available from the [DBpedia Spotlight wiki](https://github.com/dbpedia-spotlight/dbpedia-spotlight/wiki).

## FAQ

Check the [FAQ here](https://github.com/dbpedia-spotlight/dbpedia-spotlight/wiki/faq)


## Maintainers

<a href="http://infai.org"><img src="https://infai.org/wp-content/uploads/2017/08/InfAI-Logo.png" align="left" height="20%" width="20%" ></a>
