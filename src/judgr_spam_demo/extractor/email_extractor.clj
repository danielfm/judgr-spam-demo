(ns judgr-spam-demo.extractor.email-extractor
  (:use [judgr.core]
        [judgr.extractor.base])
  (:require [judgr.extractor.english-extractor]
            [judgr-spam-demo.extractor.content-handler])
  (:import  [java.io ByteArrayInputStream]
            [org.apache.james.mime4j.parser MimeStreamParser]
            [judgr.extractor.english_extractor EnglishTextExtractor]
            [judgr_spam_demo.extractor.content_handler SimpleContentHandler]))

(deftype EmailExtractor [base-extractor]
    FeatureExtractor

    (extract-features [fe item]
      (let [parser (MimeStreamParser.)
            handler (SimpleContentHandler. (atom "") (atom ""))]

        (.setContentHandler parser handler)
        (.parse parser (ByteArrayInputStream. (.getBytes item "UTF-8")))

        (apply into
               (map #(.extract-features base-extractor %)
                    [(deref (.body handler)) (deref (.subject handler))])))))

;; Registers a factory function that returns this extractor

(defmethod extractor-from :english-eml [settings]
  (let [base-extractor (EnglishTextExtractor.)]
    (EmailExtractor. base-extractor)))