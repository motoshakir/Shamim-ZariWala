import LoginPage from "@/pages/LoginPage"
import { Toaster } from "sonner"


function App() {
  return (
  
  <>
    <div className="flex min-h-svh flex-col items-center justify-center">
    <LoginPage />
    </div>
    <Toaster position="bottom-left" richColors />
    </>
  )
}

export default App