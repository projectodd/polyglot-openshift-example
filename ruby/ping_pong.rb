class PingPong < Sinatra::Base
  include TorqueBox::Injectors

  set :haml, :format => :html5

  get '/ping' do
    queue = inject('/queues/ruby')
    message = "hello from ruby at #{Time.now.to_i}"
    queue.publish(message, :encoding => :text)
    haml :ping_pong, :locals => { :message => "Published #{message}" }
  end

  get '/pong' do
    queue = inject('/queues/clojure')
    message = queue.receive(:timeout => 100)
    haml :ping_pong, :locals => { :message => "Received #{message}" }
  end

  get '/' do
    haml :ping_pong, :locals => { :message => "Hello from Ruby!" }
  end
end
