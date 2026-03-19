import mainBg from "../assets/images/main-bg.jpg"

interface Props {
  children: React.ReactNode
}

export default function CenterLayout({ children }: Props) {
  return (
    <div className="relative flex items-center justify-center min-h-screen px-4 overflow-hidden">
      <div
        aria-hidden
        className="absolute inset-0 bg-cover bg-center"
        style={{ backgroundImage: `url(${mainBg})` }}
      />
      <div
        aria-hidden
        className="absolute inset-0 bg-gradient-to-b from-black/80 via-black/60 to-black/90"
      />
      <div className="relative w-full max-w-xl">{children}</div>
    </div>
  )
}