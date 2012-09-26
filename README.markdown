# Judgr Spam Classification Demo

This is a sample application built on top of
[Judgr](http://danielfm.github.com/judgr/), a naïve Bayes classifier
library written in Clojure.

## Getting Started

First, clone this repository and start a REPL by running `lein repl`
in its root directory.

Then, load the core namespace:

````clojure

user=> (use 'judgr-spam-demo.core)
nil
````

### Training The Classifier

This repository comes with a few thousand messages for training and
testing the classifier. See _License_ section below for further
information.

The following command will train the classifier using the messages
stored in `data/training`:

````clojure

user=> (time (train!))
"Elapsed time: 20983.71 msecs"
````

This operation might take several seconds to finish.

### Trying It Out

Choose a few messages from `data/testing` and see if the classifier
got them right:

````clojure

user=> (.classify classifier (slurp "data/testing/TEST_XXXXX.eml"))
:spam
````

If you are curious about how a specific feature are distributed
between spam and ham messages:

````clojure

user=> (.get-feature (.db classifier) "viagra")
{:feature "viagra", :total 42, :classes {:spam 41, :ham 1}}
````

### Evaluating The Classifier

Use the `judgr.cross-validation` namespace to generate a Confusion
Matrix and analyze the results.

The following example shows how to perform a 10-Fold Cross Validation:

````clojure

user=> (use 'judgr.cross-validation)
nil

user=> (def conf-matrix (k-fold-crossval 10 classifier))
#'user/conf-matrix

user=> (float (accuracy conf-matrix))
0.9310345
````

This operation might take several minutes to finish.

## License

### Email Dataset

The repository includes a subset of the CSDMC2010 SPAM corpus, which is
one of the datasets for the data mining competition associated with
ICONIP 2010.

Copyright for the text in the messages remains with the original senders.

The complete dataset can be downloaded
[here](http://csmining.org/index.php/spam-email-datasets-.html).

### Code

Copyright (C) Daniel Fernandes Martins

Distributed under the New BSD License. See COPYING for further details.
