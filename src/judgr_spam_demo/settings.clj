(ns judgr-spam-demo.settings
  (:use [judgr.settings]))

(def app
  (update-settings settings

                   ;; These are the labels, or classes, we are interested in
                   [:classes] [:ham :spam]

                   ;; For a message to be labeled as spam, its probability must
                   ;; at least 4x greater than the probability of it being ham.
                   ;; This means that we only flag a message as spam if we are
                   ;; quite right about it being spam.
                   ;;
                   ;; On the other hand, if the probability of a message being
                   ;; ham is 2x  greater than the probability of it being spam,
                   ;; that's enough.
                   [:classifier :default :thresholds] {:ham 4 :spam 4}

                   ;; Uses our custom feature extractor that knows how to
                   ;; extract data from *.eml files
                   [:extractor :type] :english-eml

                   ;; Stores the training data in memory
                   [:database :type] :memory))