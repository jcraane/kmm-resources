import SwiftUI
import shared

func greet() -> String {
    return Greeter().greet()
}

struct ContentView: View {
    var body: some View {
        VStack {
            Text("test")
//            Text(greet())
//            Text(L.Companion().greetings.hello())
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
