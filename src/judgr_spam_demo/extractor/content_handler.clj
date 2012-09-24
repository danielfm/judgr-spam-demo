(ns judgr-spam-demo.extractor.content-handler
  (:import [org.apache.james.mime4j.parser ContentHandler]))

;; Implementation of Mime4J's ContentHandler, which will be used
;; to extract the subject and body of *.eml files

(deftype SimpleContentHandler [subject body]
  ContentHandler

  (body [fe bd is]
    (when (= "text" (.getMediaType bd))
      (swap! body str " " (slurp is))))

  (field [fe field]
    (when (= "Subject" (.getName field))
      (swap! subject str " " (.getBody field))))

  ;; These methods are not needed for now
  (epilogue [fe is])
  (preamble [fe is])
  (raw [fe is])
  (endBodyPart [fe])
  (endHeader [fe])
  (endMessage [fe])
  (endMultipart [fe])
  (startBodyPart [fe])
  (startHeader [fe])
  (startMessage [fe])
  (startMultipart [fe bd]))