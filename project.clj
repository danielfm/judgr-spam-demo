(defproject judgr-spam-demo "0.1.0"
  :description "Spam classifier demo application using Judgr,
a Naive Bayes classifier library."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.apache.james/apache-mime4j-core "0.7.2"]
                 [judgr/judgr "0.3.0"]]
  :jvm-opts ["-Xmx512m -Xms768m"])