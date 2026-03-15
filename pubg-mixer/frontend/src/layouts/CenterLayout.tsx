interface Props {
  children: React.ReactNode
}

export default function CenterLayout({ children }: Props) {
  return (
    <div className="flex items-center justify-center min-h-screen bg-slate-900 px-4">
      {children}
    </div>
  )
}