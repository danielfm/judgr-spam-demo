(ns judgr-spam-demo.core
  (:use [clojure.java.io]
        [judgr.core])
  (:require [judgr-spam-demo.settings :as settings]
            [judgr-spam-demo.extractor.email-extractor]))

;; Initializes the classifier
(def classifier (classifier-from settings/app))

(defn- only-eml
  "Returns true if file name ends with .eml."
  [file]
  (and (re-find #".*\.eml$" (.getName file)) (.isFile file)))

(defn- train-from-dir!
  "Feeds the classifier with messages as *.eml files stored in
directory dir as class (:spam or :ham)."
  [dir class]
  (let [items (filter only-eml (file-seq (file dir)))]
    (.train-all! classifier (map slurp items) class)))

(defn train!
  "Feeds the classifier with training examples for both classes."
  []
  (doseq [class (:classes settings/app)]
    (train-from-dir! (str "./data/training/" (name class) \/) class)))