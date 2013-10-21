(ns pustota.grabber
  (:use [clojure.core.async :only [chan go >!]])
  (:require [net.cgrand.enlive-html :as html]
            [clojure.string :as str]))

(defmacro swallow-exceptions [& body]
    `(try ~@body (catch Exception e#)))

(def pustota-url "http://vpustotu.ru/moderation/")

(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))

(defn blank-to-nil [s] (if (str/blank? s) nil s))
  
(defn parse-url
  []
  (-> pustota-url
    fetch-url
    (html/select [:div.fi_text])
    first
    html/text
    str/trim
    blank-to-nil))

(defn grab
  "Создает workers потоков и возвращает канал."
  [workers]
  (let [c (chan)]
    (do
      (doseq [i (range workers)]
        (go
         (while true
           (let [data (swallow-exceptions (parse-url))]
             (if (not (nil? data))
               (>! c data)))
           (Thread/sleep 1000))))
      (println "Running threads: " workers)
      c)))
